package br.com.drwars.repositories.jpa;

import br.com.drwars.exception.NotInsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Repository
public class EmpresaJPARepository {

	@Value("${spring.datasource.url}")
	private String dbConnection;
	
	@Value("${spring.datasource.username}")
	private String dbUser;  	

	@Value("${spring.datasource.password}")
	private String dbPassword;	
	
	private void doUpdate( String sql ) {
		
		System.out.println( sql );
		String result = "";
		try (Connection connection = DriverManager.getConnection( dbConnection, dbUser, dbPassword)) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new NotInsertException("Erro  na inclusõo de geom");
        }
	}	

	/*
		select ep.razao_social, ep.cnpj, ep.nome_fantasia,  
		en.logradouro, en.numero, en.complemento, en.bairro, en.cep, en.cidade, en.estado, en.telefone, en.email
		from tb_empresas ep
		join tb_enderecos en on en.id_endereco = ep.id_endereco	
	*/
	public void updateGeomDutos(Long idEmpresa, String geom) {

        String sql = " update public.tb_empresas " +
					" set  area_atuacao_dutos='"+geom+"'" +
					"  where id_empresa= "+idEmpresa+" ;";
		doUpdate( sql );
	}
	public void updateGeomRodoviaria(Long idEmpresa, String geom) {

		String sql = " update public.tb_empresas " +
				" set  area_atuacao_rodoviaria='"+geom+"'" +
				"  where id_empresa= "+idEmpresa+" ;";
		doUpdate( sql );
	}
	
}
