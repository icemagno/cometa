package br.com.drwars.v1.controller;

import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.FreteTipoService;
import br.com.drwars.v1.vo.FreteTipoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("FreteTipoController1")
@Tag(name = "FreteTipo")
@RequestMapping(value="/v1/freteTipo")
public class FreteTipoController {

    @Autowired
    private FreteTipoService service;

    @GetMapping
    @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = FreteTipoVO.class)))})
    @Operation(summary = "Buscar Frete Tipo", description = "Listar todas os frete tipo",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")},parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"FreteTipo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = FreteTipoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<List<FreteTipoVO>> findAll() {
        var list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Buscar  FreteTipo por id",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"FreteTipo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = FreteTipoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
   public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        var vo =  service.findById(id);
        return ReponseUtil.getResponseGet(vo);
    }


}
