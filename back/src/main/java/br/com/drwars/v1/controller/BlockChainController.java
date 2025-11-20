package br.com.drwars.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.drwars.v1.service.BlockchainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("BlockChainControllerV1")
@Tag(name = "BlockChainController")
@RequestMapping(value="/v1/blockchain")
public class BlockChainController {

    @Autowired
    private BlockchainService service;

    @GetMapping(value="/oferta/{id}")
    @Operation(description = "Buscar uma transação de oferta por id",method = "GET",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
	)
	public String findById(@PathVariable("id") Long id) {
    	br.com.drwars.blockchain.OfertasContract.Oferta vo = service.getOferta(id);
    	String content = vo.content;
    	return  content;
	}

}
