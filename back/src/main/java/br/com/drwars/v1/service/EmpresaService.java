package br.com.drwars.v1.service;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoEmpresa;
import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoEmpresaListar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import br.com.drwars.repositories.jpa.EnderecoJPARepository;
import br.com.drwars.v1.vo.EmpresaResumidaVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.EmpresaSituacao;
import br.com.drwars.entites.EmpresaTipo;
import br.com.drwars.entites.Endereco;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.EmpresaRepository;
import br.com.drwars.repositories.EnderecoRepository;
import br.com.drwars.repositories.jpa.EmpresaJPARepository;
import br.com.drwars.repositories.jpa.MunicipioJPARepository;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.RepresentanteVO;

@Service
public class EmpresaService {

    private Logger logger = Logger.getLogger(EmpresaService.class.getName());
    
    @Autowired private GeocodingService geocodingService;

    @Autowired private EnderecoJPARepository enderecoJPARepository;

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private MunicipioJPARepository municipioRepository;

    @Autowired
    private EmpresaJPARepository empresaJPARepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private RepresentanteService representanteService;

    @Autowired
    private UsuarioService usuarioService;

    public  Page<EmpresaVO> findByRazaoSocial(String razaoSocial,List<Long> tipo, Pageable pageable) {
        logger.warning("Listar Empresass");
        validaPermissaoEmpresaListar();
        Page<Empresa>  listaPage = null;
        if(razaoSocial==null || razaoSocial.isBlank()){
            listaPage= repository.findByTipos(tipo,pageable);
        }else {
            listaPage = repository.findByRazaoSocialTipos(razaoSocial,tipo, pageable);
        }

        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,EmpresaVO.class));
        return listaVosPage;
    }


    public  Page<EmpresaVO> withoutLoggedCompany(String razaoSocial,List<Long> tipo, Pageable pageable) {
        logger.warning("Listar Empresass");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());

        Page<Empresa>  listaPage = null;
   /*     if(razaoSocial==null || razaoSocial.isBlank()){
            listaPage= repository.findByTipos(tipo,pageable);
        }else {
            listaPage = repository.findByRazaoSocialTipos(razaoSocial,tipo, pageable);
        }
*/
        listaPage= repository.findMenosEmpresa(usuario.getEmpresa().getId(),pageable);

        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p, EmpresaVO.class));
        return listaVosPage;
    }


    public EmpresaVO findById(Long id) {
        logger.warning("Buscar empresa por id");
        validaPermissaoEmpresaListar();
        var model = (Empresa) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa  não  encontrado!"));
        if(model !=null)
            return DozerConverter.parseObject(model,EmpresaVO.class);
        else
            return  null;
    }

    public Empresa findEmpresaById(Long id) {
        logger.warning("Buscar empresa por id");
        var model = (Empresa) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa  não  encontrado!"));
        return model;
    }

    public void updateEmpresa(Long  idEmpresa) throws CampoInvalidoException {

        var empresa = this.findById(idEmpresa);
        var endereco = empresa.getEndereco();

        String endCompl = endereco.getLogradouro() + " " +
                endereco.getNumero() + " " +

                endereco.getBairro() + " " +
                endereco.getCidade() + " " +
                endereco.getEstado() + " " +
                endereco.getCep();

        JSONObject coords = geocodingService.getCoordinates(endCompl);
        String lat = coords.getString("lat");
        String lon = coords.getString("lon");
        enderecoJPARepository.update(endereco.getId(),lat,lon);

      //  return empresaVO;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public EmpresaVO create(EmpresaVO vo) throws CampoInvalidoException {
        logger.warning("Cadastrar empresa");
        var model = new Empresa();

        var existsEmpresa = repository.findEmpresaByCnpj(vo.getCnpj());
        if(existsEmpresa!=null){
            throw new CampoInvalidoException("Esse  cnpj já está cadastrado!");
        }

        preecherModel(vo, model);
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        model.setDataInclusao(LocalDateTime.now());
        model.setUsuarioInclusao(UsuarioLogado.getUser());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        Endereco endereco=model.getEndereco();
        enderecoRepository.save(endereco);

        
        
        model.setEndereco(endereco);
        repository.save(model);
        var empresaVO = DozerConverter.parseObject(model,EmpresaVO.class);

        if(vo.getRepresetantes()!=null && !vo.getRepresetantes().isEmpty()){
            empresaVO.setRepresetantes(new ArrayList<>());
            for(RepresentanteVO representanteVO : vo.getRepresetantes()){
                var r = representanteService.create(representanteVO, model);
                empresaVO.getRepresetantes().add(r);
            }
        }
        return  empresaVO;

    }

    public void updateGeom(Long id, EmpresaVO vo) throws CampoInvalidoException {
        if(vo.getListaMunicipiosDutos()!=null && !vo.getListaMunicipiosDutos().isEmpty()){
            String geom = this.municipioRepository.findGeom(vo.getListaMunicipiosDutos());
            empresaJPARepository.updateGeomDutos(id,geom);
        }

        if(vo.getListaMunicipiosRodoviaria()!=null && !vo.getListaMunicipiosRodoviaria().isEmpty()){
            String geom = this.municipioRepository.findGeom(vo.getListaMunicipiosRodoviaria());
            empresaJPARepository.updateGeomRodoviaria(id,geom);
        }
    }



    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public EmpresaVO update(EmpresaVO vo) throws CampoInvalidoException {
        logger.warning("Alterar empresa");
        validaPermissaoEmpresa();
        var model = repository.findById(vo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa  não  encontrado!"));

        var existsEmpresa = repository.findEmpresaByCnpj(vo.getCnpj());
        if(existsEmpresa!=null && !existsEmpresa.getId().equals(vo.getId())){
            throw new CampoInvalidoException("Esse  cnpj já está cadastrado!");
        }

        preecherModel(vo, model);

        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        var endereco=model.getEndereco();
        enderecoRepository.save(endereco);
        model.setEndereco(endereco);
        repository.save(model);

        if(!vo.getRepresetantes().isEmpty()) {
            for (RepresentanteVO representanteVO : vo.getRepresetantes()) {
                representanteService.create(representanteVO, model);
            }
        }


        return DozerConverter.parseObject(model,EmpresaVO.class);
    }


    private static void preecherModel(EmpresaVO vo, Empresa model) {
        model.setCnpj(vo.getCnpj());
        model.setRazaoSocial(vo.getRazaoSocial());
        model.setNomeFatasia(vo.getNomeFatasia());
        model.setEndereco(DozerConverter.parseObject(vo.getEndereco(), Endereco.class));
        model.setEmpresaSituacao(DozerConverter.parseObject(vo.getEmpresaSituacao(), EmpresaSituacao.class));
        if(vo.getEmpresaTipo()!=null && vo.getEmpresaTipo().getId() >0)
         model.setEmpresaTipo(DozerConverter.parseObject(vo.getEmpresaTipo(), EmpresaTipo.class));
        model.setInscricaoEstadual(vo.getInscricaoEstadual());
        model.setInscricaoMunicipal(vo.getInscricaoMunicipal());
        model.setNichoMercado(vo.getNichoMercado());
        model.setServicosProdutos(vo.getServicosProdutos());
        model.setPorteEmpresa(vo.getPorteEmpresa());
    }
}
