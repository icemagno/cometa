package br.com.drwars.v1.controller;

import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.PerfilService;
import br.com.drwars.v1.vo.PerfilVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@RestController("PerfilControllerV1")
@Tag(name = "Perfil")
@RequestMapping(value="/v1/perfis")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @GetMapping
    @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))})
    @Operation(summary = "Buscar perfil por nome ", description = "Listar todos  os perfis usando com a possibilidade de passar o nome como parâmetro",
                method = "GET",security = {@SecurityRequirement(name = "Bearer")},parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
                tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<Page<PerfilVO>> findPerfilByNome(@RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="size", defaultValue = "12") int size,
            @RequestParam(value="direction", defaultValue = "asc") String direction,
            @RequestParam(value="nome", required = false) String nome
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection, "id"));
        var list = service.findPerfilByNome(nome,pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Buscar  perfil por id",method = "GET",
                security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
                tags = {"Perfil"},
                responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
   public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        PerfilVO vo =  service.findById(id);
        return ReponseUtil.getResponseGet(vo);
    }

    @PostMapping
    @Operation(description = "Cadastrar  perfil ",method = "POST", security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> create(@RequestBody PerfilVO vo) throws CampoInvalidoException {
        var model =  service.create(vo);
        return ReponseUtil.getResponseGet(model);
    }

    @PutMapping(value="/{id}")
    @Operation(description = "Alterar  perfil ",method = "PUT", security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody PerfilVO vo) throws CampoInvalidoException {
        vo.setKey(id);
        vo =  service.update(vo);
        return ReponseUtil.getResponseGet(vo);
    }


}
