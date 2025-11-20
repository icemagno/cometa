package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Certificado;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.CertificadoRepository;
import br.com.drwars.v1.vo.certificadora.CertificadoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

import static br.com.drwars.util.security.ValidaPermissao.*;

@Service
public class CertificadoService {

    private Logger logger = Logger.getLogger(CertificadoService.class.getName());

    @Autowired
    private CertificadoRepository repository;

    @Autowired
    private EmpresaService empresaService;

    public  Page<CertificadoVO> findAll(Pageable pageable) {
        logger.warning("Listar Certificado");
        validaPermissaoCertificadoraListar();
        Page<Certificado>  listaPage = repository.findAll(pageable);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,CertificadoVO.class));
        return listaVosPage;
    }

    public CertificadoVO findById(Long id) {
        logger.warning("Buscar Certificado por id");
        validaPermissaoCertificadoraListar();
        var model = (Certificado) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa  não  encontrado!"));
        if(model !=null)
            return DozerConverter.parseObject(model,CertificadoVO.class);
        else
            return  null;
    }

    public Certificado findCertificadoById(Long id) {
        logger.warning("Buscar Certificado por id");
        validaLogado();
        var model = (Certificado) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificado  não  encontrado!"));
        return  model;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public CertificadoVO create(CertificadoVO vo) throws CampoInvalidoException {
        logger.warning("Cadastrar Certificado");
        validaPermissaoCertificadora();
        var model = new Certificado();

        var empresa = empresaService.findEmpresaById(vo.getEmpresa().getId());
        var empresaCertificadora = empresaService.findEmpresaById(vo.getEmpresaCertificadora().getId());

        model.setEquivalencia(vo.getEquivalencia());
        model.setQuantidade(vo.getQuantidade());
        model.setNumero(vo.getNumero());
        model.setEmpresaCertificadora(empresaCertificadora);
        model.setEmpresa(empresa);

        repository.save(model);

        return  DozerConverter.parseObject(model,CertificadoVO.class);

    }



    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public CertificadoVO update(CertificadoVO vo) throws CampoInvalidoException {
        validaPermissaoCertificadora();
        var model = repository.findById(vo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Certificadora  não  encontrado!"));

        var empresa = empresaService.findEmpresaById(vo.getEmpresa().getId());
        var empresaCertificadora = empresaService.findEmpresaById(vo.getEmpresaCertificadora().getId());

        model.setEquivalencia(vo.getEquivalencia());
        model.setQuantidade(vo.getQuantidade());
        model.setNumero(vo.getNumero());
        model.setEmpresaCertificadora(empresaCertificadora);
        model.setEmpresa(empresa);
        repository.save(model);


        return DozerConverter.parseObject(model,CertificadoVO.class);
    }



}
