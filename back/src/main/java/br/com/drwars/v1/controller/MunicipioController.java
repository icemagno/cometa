package br.com.drwars.v1.controller;

import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.EstadoService;
import br.com.drwars.v1.service.MunicipioService;
import br.com.drwars.v1.vo.EstadoVO;
import br.com.drwars.v1.vo.MunicipioVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MunicipioControllerV1")
@Tag(name = "Municipio")
@RequestMapping(value="/v1/municipios")
public class MunicipioController {
	
    @Autowired
    private MunicipioService service;

    @GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar todos municípios",method = "GET", tags = {"Municipio"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = MunicipioVO.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> findAll() {

        var lista = service.findAll();
        return ReponseUtil.getResponseGet(lista);
    }

    @GetMapping(value="/estado/{uf}", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar todos municípios por estado",method = "GET", tags = {"Municipio"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = MunicipioVO.class)))
                    }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    public ResponseEntity<Object> findAllByUf(@PathVariable("uf") String uf) {

        var lista = service.findByUf(uf);
        return ReponseUtil.getResponseGet(lista);
    }
}
