package br.com.drwars.v1.controller;

import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.LocalService;
import br.com.drwars.v1.vo.compra.CompraVO;
import br.com.drwars.v1.vo.LocalVO;
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

@RestController("LocalControllerV1")
@Tag(name = "Local")
@RequestMapping(value="/v1/locais")
public class LocalController {

    @Autowired
    private LocalService service;

    @GetMapping
    @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CompraVO.class)))})
    @Operation(summary = "Buscar Locais ", description = "Listar todas  os locais",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")},parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Local"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = LocalVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<List<LocalVO>> findAll() {
        var list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Buscar  local por id",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Local"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = LocalVO.class)))}),
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

    @PostMapping
    @Operation(description = "Cadastrar  Local ",method = "POST", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Local"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = LocalVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> create(@RequestBody LocalVO vo) {
        var model =  service.create(vo);
        return ReponseUtil.getResponseGet(model);
    }

    @PutMapping(value="/{id}")
    @Operation(description = "Alterar  Local ",method = "PUT", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Local"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CompraVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody LocalVO vo) {
        vo.setKey(id);
        vo =  service.update(id,vo);
        return ReponseUtil.getResponseGet(vo);
    }


}
