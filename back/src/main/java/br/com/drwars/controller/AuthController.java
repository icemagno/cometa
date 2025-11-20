package br.com.drwars.controller;

import br.com.drwars.sevice.AuthServices;
import br.com.drwars.v1.vo.security.AccountCredentialsVO;
import br.com.drwars.v1.vo.security.TokenVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController("AuthController")
@Tag(name = "Auth")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthServices authServices;
	
	@Operation(description = "Logar ",method = "POST")
	@ApiResponse(responseCode = "200",content = {@Content(schema = @Schema(implementation = TokenVO.class))})
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (checkIfParamsIsNotNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = authServices.signin(data);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return token;
	}
	

	@Operation(description = "Refresh Token ",method = "PUT")
	@ApiResponse(responseCode = "200",content = {@Content(schema = @Schema(implementation = TokenVO.class))})
	@PutMapping(value = "/refresh")
	public ResponseEntity refreshToken(@RequestParam("username") String username, @RequestHeader("Authorization") String refreshToken) {
		if (checkIfParamsIsNotNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = authServices.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return token;
	}

	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() ||
				username == null || username.isBlank();
	}

	private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank()
				 || data.getPassword() == null || data.getPassword().isBlank();
	}
}
