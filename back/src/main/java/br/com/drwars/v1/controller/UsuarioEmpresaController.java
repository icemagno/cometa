package br.com.drwars.v1.controller;

import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.EmpresaService;
import br.com.drwars.v1.service.UsuarioService;
import br.com.drwars.v1.vo.UsuarioEmpresaVO;
import br.com.drwars.v1.vo.UsuarioVO;
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


@RestController("UsuarioEmpresaControllerV1")
@Tag(name = "UsuarioEmpresa")
@RequestMapping(value="/v1/usuario/empresa")
public class UsuarioEmpresaController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Operation(description = "Cadastrar  usuario ",method = "POST", security = {@SecurityRequirement(name = "Bearer")}, parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Usuario"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UsuarioVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity create(@RequestBody UsuarioEmpresaVO vo) throws CampoInvalidoException {

        var model =  service.createUsuarioEmpresa(vo);
        try{
            empresaService.updateGeom(model.getIdempresa(),vo.getEmpresa());
            empresaService.updateEmpresa(model.getIdempresa());
        }catch (Exception e){}


        return ResponseEntity.ok(model);
    }



}
