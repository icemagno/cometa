package br.com.drwars.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.drwars.v1.service.GeneratePDFService;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.minidev.json.JSONObject;

@RestController("PDFControllerV1")
@Tag(name = "Gerador de PDF")
@RequestMapping(value="/v1/pdf")
public class PDFTestController {
	
    @Autowired
    private GeneratePDFService generatePDFService;	

    @GetMapping(value="/test", produces = MediaType.APPLICATION_PDF_VALUE )
    public ResponseEntity<InputStreamResource> testPdf(   ) {
    	
    	JSONObject jo = new JSONObject();
    	jo.put("modalidade", "(MODALIDADE)");
    	jo.put("poderCalorifico", "5678");
    	jo.put("pressaoFornecimento", "234");
    	jo.put("vazaoFornecimento", "456");
    	jo.put("poderCalorificoRef", "123");
    	jo.put("poderCalorificoSup", "678");
    	jo.put("vendedora", "Vendedora S/A");
    	jo.put("compradora", "Compradora LTDA");
    	jo.put("dataInicio", "16/11/2023");

    	return generatePDFService.generateContrato(jo);
    }    
    
}
