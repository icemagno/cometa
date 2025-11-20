package br.com.drwars.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.drwars.v1.service.MapaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("MapaControllerV1")
@Tag(name = "Mapa")
@RequestMapping(value="/v1/mapa")
public class MapaController {
	
    @Autowired
    private MapaService service;	

    @GetMapping(value="/empresas", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar todas as empresas no mapa",method = "GET", tags = {"Empresa"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public String findAllEmpresas() {
        return service.findAllEmpresas();
    }    

    
    @GetMapping(value="/dutos", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar todos os dutos no mapa",method = "GET", tags = {"Duto"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public String findAllDutos() {
        return service.findAllDutos();
    }
    
    @GetMapping(value="/coleta", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar todos os pontos de coleta no mapa",method = "GET", tags = {"Empresa"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public String findAllPontosColeta() {
        return service.findAllPontosColeta();
    }  
    
    
    
    
    @GetMapping(value="/areas/dutos/{id}", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar áreas de atuação por dutos de uma empresa no mapa",method = "GET", tags = {"Duto"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public String getAreaDutos( @PathVariable("id") Integer id ) {
        return service.getAreaDutos( id );
    }
    
    @GetMapping(value="/areas/rodovias/{id}", produces=MediaType.APPLICATION_JSON_VALUE )
    @Operation(description = "Listar áreas de atuação rodoviária de uma empresa no mapa",method = "GET", tags = {"Duto"}, responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))
                        }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public String getAreaRodovias( @PathVariable("id") Integer id ) {
        return service.getAreaRodovias( id );
    }    
    
    
    
    
    
    
}
