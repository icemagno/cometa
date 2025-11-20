package br.com.drwars.v1.controller;

import br.com.drwars.util.ReponseUtil;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.v1.service.EmpresaService;
import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.PerfilVO;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("EmpresaControllerV1")
@Tag(name = "Empresa")
@RequestMapping(value="/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @GetMapping
    @Operation(description = "Listar todas empresas podendo filtrar pela razão social",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Empresa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = EmpresaVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> findByRazaoSocial(@RequestParam(value="page", defaultValue = "0") int page,
          @RequestParam(value="size", defaultValue = "12") int size,
          @RequestParam(value="direction", defaultValue = "asc") String direction,
          @RequestParam(value="razaosocial", required = false) String razaoSocial,
          @RequestParam(value = "tipo", required = true) List<Long> tipo) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection, "id"));
        var list = service.findByRazaoSocial(razaoSocial,tipo,pageable);
        return ReponseUtil.getResponseGet(list);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Recuperar  empresa por id",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Empresa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmpresaVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<EmpresaVO> findById(@Parameter(name = "id",example = "1", required = true) @PathVariable(value = "id",  required = true) Long id) {
        EmpresaVO vo =  service.findById(id);
        if(vo==null)
            ResponseEntity.notFound();
        return ResponseEntity.ok(vo);
    }

    @PostMapping
    @Operation(description = "Cadastrar  empresa ",method = "POST",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Empresa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmpresaVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<EmpresaVO> create(@Parameter(required = true) @RequestBody EmpresaVO vo) throws CampoInvalidoException {
        var model =  service.create(vo);
       // service.updateEmpresa(model);
        return ResponseEntity.ok(model);
    }

    @PutMapping(value="/{id}")
    @Operation(description = "Alterar  empresa ",method = "PUT",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Empresa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = EmpresaVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<EmpresaVO> update(
            @Parameter(name = "id",example = "1", required = true) @PathVariable(value = "id",  required = true) Long id,
            @Parameter(required = true) @RequestBody EmpresaVO vo) throws CampoInvalidoException {
        vo.setId(id);
        vo =  service.update(vo);
        return ResponseEntity.ok(vo);
    }


    @GetMapping(value = "/withoutLoggedCompany")
    @Operation(description = "Listar todas empresas podendo filtrar pela razão social",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Empresa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = EmpresaVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> withoutLoggedCompany(@RequestParam(value="page", defaultValue = "0") int page,
                                                       @RequestParam(value="size", defaultValue = "12") int size,
                                                       @RequestParam(value="direction", defaultValue = "asc") String direction,
                                                       @RequestParam(value="razaosocial", required = false) String razaoSocial,
                                                       @RequestParam(value = "tipo", required = true) List<Long> tipo) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection, "id"));
        var list = service.withoutLoggedCompany(razaoSocial,tipo,pageable);
        return ReponseUtil.getResponseGet(list);
    }

}
