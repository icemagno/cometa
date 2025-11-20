package br.com.drwars.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.drwars.security.jwt.JwtConfigurer;
import br.com.drwars.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider tokenProvider;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/auth/signin").permitAll()
				.antMatchers("/v1/estados/**").permitAll()
				.antMatchers("/v1/blockchain/**").permitAll()
				.antMatchers("/v1/pdf/**").permitAll()
				.antMatchers("/v1/municipios/**").permitAll()
				.antMatchers("/v1/mapa/**").permitAll()
				.antMatchers("/v1/noticias/aprovadas").permitAll()
				.antMatchers("/v1/usuario/empresa").permitAll()
				.antMatchers("/auth/refresh/**").permitAll()
				.antMatchers("/api-docs/**").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/drwars/api/**").authenticated()

				.and()
				.apply(new JwtConfigurer(tokenProvider));

		super.configure(http);

	}

	//  /drwars/api
}
