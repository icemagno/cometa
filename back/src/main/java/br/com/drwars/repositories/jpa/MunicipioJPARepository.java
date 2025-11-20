package br.com.drwars.repositories.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class MunicipioJPARepository {

	@Value("${spring.datasource.url}")
	private String dbConnection;
	
	@Value("${spring.datasource.username}")
	private String dbUser;  	

	@Value("${spring.datasource.password}")
	private String dbPassword;	
	
	private String doRequest( String sql ) {
		
		System.out.println( sql );
		
		String result = "";
		try (Connection connection = DriverManager.getConnection( dbConnection, dbUser, dbPassword); PreparedStatement preparedStatement = connection.prepareStatement( sql )) {
            try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
            	resultSet.next();
                result = resultSet.getString("featurecollection");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return result;
	}	

	/*
		select ep.razao_social, ep.cnpj, ep.nome_fantasia,  
		en.logradouro, en.numero, en.complemento, en.bairro, en.cep, en.cidade, en.estado, en.telefone, en.email
		from tb_empresas ep
		join tb_enderecos en on en.id_endereco = ep.id_endereco	
	*/
	public String findGeom(List<Long> municipios) {
		var where = "( ";
		var first=true;
		for (Long id : municipios){
			if(!first){
				where += ",";
			}
			where += ""+id;
			first=false;
		}

		where+=")";

        String sql = " select st_union(geom) as featurecollection " +
				" from public.ibge_municipio mun " +
				" inner join public.ibge_municipio_regiao_geom geo on mun.codigo = cast(geo.cd_geocodm as integer) " +
				" where mun.codigo in "+where+" ;";
		return doRequest( sql );
	}
	
	
}
