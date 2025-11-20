package br.com.drwars.entites;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_usuarios_id_usuario_seq")
	@SequenceGenerator(name = "tb_usuarios_id_usuario_seq", sequenceName = "tb_usuarios_id_usuario_seq", allocationSize = 1)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "username", unique = true)
	private String userName;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "nome_completo")
	private String fullName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "account_non_expired",nullable=false)
	private Boolean accountNonExpired;
	
	@Column(name = "account_non_locked",nullable=false)
	private Boolean accountNonLocked;
	
	@Column(name = "credentials_non_expired",nullable=false)
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled",nullable=false)
	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name="id_perfil",referencedColumnName = "id_perfil")
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_inclusao",referencedColumnName = "id_usuario")
	private Usuario usuarioInclusao;

	@ManyToOne
	@JoinColumn(name="id_usuario_alteracao", referencedColumnName = "id_usuario")
	private Usuario usuarioAlteracao;

	@Column(name = "dt_inclusao")
	private LocalDateTime dataInclusao;

	@Column(name = "dt_alteracao")
	private LocalDateTime dataAlteracao;

	public Usuario() {}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		if(perfil!=null && perfil.getSituacao()){
			roles.add(perfil.getNome().toUpperCase());
		}

		return roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfil!=null ? Arrays.asList(this.perfil) : null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}



}