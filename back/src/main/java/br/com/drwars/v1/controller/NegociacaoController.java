package br.com.drwars.v1.controller;

import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.util.ReponseUtil;
import br.com.drwars.v1.service.NegociacaoService;
import br.com.drwars.v1.vo.negociacao.NegociacaoVO;
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

import javax.mail.MessagingException;


@RestController("NegociacaoControllerV1")
@Tag(name = "Negociacao")
@RequestMapping(value="/v1/negociacoes")
public class NegociacaoController {

    @Autowired
    private NegociacaoService service;

    @PostMapping
    @Operation(description = "Cadastrar  oferta ",method = "POST", security = {@SecurityRequirement(name = "Bearer")},
            parameters = {@Parameter(name="authorization", in= ParameterIn.HEADER, required = true,description = "Token")},
            tags = {"Negociacao"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = NegociacaoVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity create(@RequestBody NegociacaoVO vo) throws CampoInvalidoException, MessagingException {
        var model =  service.insertNegocicao(vo);
        return  ResponseEntity.ok().build();
    }




}
