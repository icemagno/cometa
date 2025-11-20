package br.com.drwars.repositories.jpa;

import br.com.drwars.exception.NotInsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class EnderecoJPARepository {

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


	public void update(Long idEndereco, String lon, String lat) {

        String sql = "UPDATE tb_enderecos set the_geom = ST_GeomFromText('POINT("+lat+" "+lon+")', 4326) where id_endereco = " + idEndereco;
		//String sql = "UPDATE tb_enderecos set numero = 10 where id_endereco = " + idEndereco;
		System.out.println(sql);
		doUpdate( sql );
	}

	
}
