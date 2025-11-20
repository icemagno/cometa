package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.RelatorioAnual;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.RequiredObjectIsNullException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.RelatorioAnualRepository;
import br.com.drwars.v1.vo.RelatorioAnualVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoPerfilPersistir;

@Service
public class RelatorioAnualService {

    @Autowired
    private RelatorioAnualRepository repository;

    @Autowired
    private CompraService compraService;

    private Logger logger = Logger.getLogger(RelatorioAnualService.class.getName());


    public List<RelatorioAnualVO> findALL() {
        logger.warning("Listar todas relatorios");
        List<RelatorioAnual> listaPage = repository.findAll();
        var listaVosPage = listaPage.stream().map(p-> DozerConverter.parseObject(p,RelatorioAnualVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }

    public RelatorioAnualVO findById(Long id) {
        logger.warning("Buscar relatorios por id");
        var model = (RelatorioAnual) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relatorio Anual  não  encontrado!"));
        var vo =  DozerConverter.parseObject(model, RelatorioAnualVO.class);
        return vo;
    }

    public ResponseEntity<InputStreamResource> download(Long id) {
        logger.warning("Download relatorios por id");
        var model = (RelatorioAnual) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relatorio Anual  não  encontrado!"));

        ByteArrayInputStream bis = new ByteArrayInputStream( model.getDocumento());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=contrato.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body( new InputStreamResource(bis) );

    }

    public List<RelatorioAnualVO> findByIdCompra(Long idContrato) {
        logger.warning("Buscar relatorios por id");
        List<RelatorioAnual> listaPage = repository.findByIdCompra(idContrato);
        var listaVosPage = listaPage.stream().map(p-> DozerConverter.parseObject(p,RelatorioAnualVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public RelatorioAnualVO create(RelatorioAnualVO vo) throws CampoInvalidoException {
        logger.warning("Cadastrar Relatorio Anual");

        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Relatorio Anual!");

        var model = new RelatorioAnual();

        model.setDataRelatorio(LocalDateTime.now());
        model.setDocumento(vo.getDocumento());
        model.setCompra(compraService.findCompraById(vo.getCompra().getId()));

        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, RelatorioAnualVO.class);
        return retorno;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public RelatorioAnualVO update(RelatorioAnualVO vo) throws CampoInvalidoException {
        logger.warning("Alterar Relatorio Anual ");
        validaPermissaoPerfilPersistir();
        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Relatorio Anual!");

        var model = repository.findById(vo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Relatorio Anual  não  encontrado!"));

        model.setDataRelatorio(LocalDateTime.now());
        model.setDocumento(vo.getDocumento());
        model.setCompra(compraService.findCompraById(vo.getCompra().getId()));
        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, RelatorioAnualVO.class);
        return retorno;
    }



}
