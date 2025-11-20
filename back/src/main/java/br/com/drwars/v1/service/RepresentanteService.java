package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.EmpresaRepresetantes;
import br.com.drwars.entites.Endereco;
import br.com.drwars.entites.Representante;
import br.com.drwars.repositories.EmpresaRepresetanteRepository;
import br.com.drwars.repositories.EnderecoRepository;
import br.com.drwars.repositories.RepresentanteRepository;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.RepresentanteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class RepresentanteService {

    private Logger logger = Logger.getLogger(RepresentanteService.class.getName());
    @Autowired
    private RepresentanteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EmpresaRepresetanteRepository empresaRepresetanteRepository;



    public RepresentanteVO create(RepresentanteVO vo, Empresa empresa)  {
        logger.warning("Cadastrar representante");
        var model = this.repository.findRepresentanteByCpf(vo.getCpf());
        if(model==null){
            model= new Representante();
            preecherModel(vo, model);
            if(vo.getEndereco()!=null){
                var endereco= DozerConverter.parseObject(vo.getEndereco(), Endereco.class);
                enderecoRepository.save(endereco);
                model.setEndereco(endereco);
            }
            repository.save(model);
        }
        //associar a empresa
        getEmpresaRepresetante(empresa, model);
        return  DozerConverter.parseObject(model, RepresentanteVO.class);
    }



    private void getEmpresaRepresetante(Empresa empresa, Representante model) {
        var empresaRepresetante = empresaRepresetanteRepository.findByIdRepresetanteAndIdEmpresa(model.getId(), empresa.getId());

        if(empresaRepresetante==null) {
            empresaRepresetante =new EmpresaRepresetantes();
            empresaRepresetante.setDataInclusao(LocalDateTime.now());
            empresaRepresetante.setUsuarioInclusao(UsuarioLogado.getUser());
            empresaRepresetante.setRepresentante(model);
            empresaRepresetante.setEmpresa(empresa);

        }
        empresaRepresetante.setDataAlteracao(LocalDateTime.now());
        empresaRepresetante.setUsuarioAlteracao(UsuarioLogado.getUser());
        empresaRepresetante.setSituacao(true);
        this.empresaRepresetanteRepository.save(empresaRepresetante);
    }

    private static void preecherModel(RepresentanteVO vo, Representante model) {
        model.setCpf(vo.getCpf());
        model.setNome(vo.getNome());
    }

}