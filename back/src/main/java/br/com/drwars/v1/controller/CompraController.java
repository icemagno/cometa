package br.com.drwars.v1.controller;

import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.CompraService;
import br.com.drwars.v1.vo.compra.CompraVO;
import br.com.drwars.v1.vo.oferta.OfertaVO;
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

@RestController("CompraControllerV1")
@Tag(name = "Compra")
@RequestMapping(value="/v1/compras")
public class CompraController {

    @Autowired
    private CompraService service;

    @GetMapping(value="/")
    @Operation(description = "Buscar  compras",method = "GET", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Compra"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = CompraVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> findAll(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="size", defaultValue = "12") int size,
            @RequestParam(value="direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection, "id"));
        var vo =  service.findAllByEmpresa(pageable);
        return ReponseUtil.getResponseGet(vo);
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Buscar  compra por id",method = "GET", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Compra"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = CompraVO.class)))}),
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

    @GetMapping
    @Operation(description = "Cadastrar  Compra ",method = "POST", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Compra"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = CompraVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> create(@RequestParam("id-oferta") Long idOferta) throws CampoInvalidoException {
        var model =  service.create(idOferta);
        return ReponseUtil.getResponseGet(model);
    }




}
