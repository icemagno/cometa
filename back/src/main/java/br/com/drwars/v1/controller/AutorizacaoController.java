package br.com.drwars.v1.controller;

import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.AutorizacaoService;
import br.com.drwars.v1.vo.autorizadora.AutorizacaoCreateVO;
import br.com.drwars.v1.vo.autorizadora.AutorizacaoVO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AutorizacaoControllerV1")
@Tag(name = "Autorizacao")
@RequestMapping(value="/v1/autorizacoes")
public class AutorizacaoController {

    @Autowired
    private AutorizacaoService service;

    @GetMapping
    @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = AutorizacaoVO.class)))})
    @Operation(summary = "Buscar autorizações", description = "Listar todos  os autorizações",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")},parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Autorizacao"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = AutorizacaoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<Page<AutorizacaoVO>> findAll(@RequestParam(value="page", defaultValue = "0") int page,
                                                       @RequestParam(value="size", defaultValue = "12") int size,
                                                       @RequestParam(value="direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection, "id"));
        var list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Buscar  autorização por id",method = "GET",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Autorizacao"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = AutorizacaoVO.class)))}),
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
    @Operation(description = "Cadastrar  autorização ",method = "POST",
            security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Autorizacao"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = AutorizacaoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> create(@RequestBody AutorizacaoCreateVO vo) {
        var model =  service.create(vo);
        return ReponseUtil.getResponseGet(model);
    }

    @PutMapping(value="/{id}")
    @Operation(description = "Alterar  autorização ",method = "PUT", security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Autorizacao"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AutorizacaoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody AutorizacaoCreateVO autorizacaoVO) {
        var vo =  service.update(id,autorizacaoVO);
        return ReponseUtil.getResponseGet(vo);
    }


}
