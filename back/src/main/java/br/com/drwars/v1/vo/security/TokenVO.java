package br.com.drwars.v1.vo.security;

import br.com.drwars.v1.vo.PerfilVO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	private PerfilVO perfil;
	private List<String> roles;
	private Long idEmpresa;
	private String razaoSocial;


}