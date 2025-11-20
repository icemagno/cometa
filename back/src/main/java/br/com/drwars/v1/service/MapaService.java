package br.com.drwars.v1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapaService {

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
	public String findAllEmpresas() {
        String sql = "SELECT CAST(row_to_json(fc) AS TEXT) AS featurecollection "
        		+ "FROM ( SELECT 'FeatureCollection' As type, array_to_json( array_agg( f ) ) As features "
        		+ "FROM (SELECT 'Feature' As type, ST_AsGeoJSON( the_geom )::json As geometry,"
        		+ "row_to_json((SELECT l FROM (SELECT id_empresa, razao_social, cnpj, nome_fantasia, logradouro, numero, complemento, bairro, cep, cidade, estado, telefone, email, compra_venda, id_empresa_tipo, ds_oferta_situacao, ds_oferta_tipo) As l)) As properties "
        		+ "FROM ( SELECT ep.id_empresa, ep.razao_social, ep.cnpj, ep.id_empresa_tipo, ep.nome_fantasia, en.logradouro, en.numero, en.complemento, en.bairro, en.cep, en.cidade, en.estado, en.telefone, en.email, "
        		+ "ofe.compra_venda, ofesit.ds_oferta_situacao, ofetip.ds_oferta_tipo, "
        		+ "en.the_geom FROM public.tb_empresas ep join public.tb_enderecos en on ep.id_endereco = en.id_endereco left join public.tb_ofertas ofe on ofe.id_empresa = ep.id_empresa "  
        		+ "left join public.tb_oferta_situacoes ofesit on ofesit.id_oferta_situacao = ofe.id_oferta_situacao " 
        		+ "left join public.tb_oferta_tipos ofetip on ofetip.id_oferta_tipo = ofe.id_oferta_tipo ) As l ) As f) as fc;";		
		return doRequest( sql );
	}
	
	public String findAllDutos() {
        String sql = "SELECT CAST(row_to_json(fc) AS TEXT) AS featurecollection "
        		+ "FROM ( SELECT 'FeatureCollection' As type, array_to_json( array_agg( f ) ) As features "
        		+ "FROM (SELECT 'Feature' As type, ST_AsGeoJSON( the_geom )::json As geometry,"
        		+ "row_to_json((SELECT l FROM (SELECT id_duto,nome_duto,diametro_duto) As l)) As properties "
        		+ "FROM ( SELECT id_duto,nome_duto,diametro_duto,the_geom FROM public.tb_dutos ) As l ) As f) as fc;";		
		return doRequest( sql );
	}

	public String findAllPontosColeta() {
        String sql = "SELECT CAST(row_to_json(fc) AS TEXT) AS featurecollection "
        		+ "FROM ( SELECT 'FeatureCollection' As type, array_to_json( array_agg( f ) ) As features "
        		+ "FROM (SELECT 'Feature' As type, ST_AsGeoJSON( the_geom )::json As geometry,"
        		+ "row_to_json((SELECT l FROM (SELECT id_local,ds_local,nm_local) As l)) As properties "
        		+ "FROM ( SELECT id_local, ds_local, nm_local, the_geom FROM tb_locais lo left join tb_enderecos en on lo.id_endereco = en.id_endereco ) As l ) As f) as fc;";		
		return doRequest( sql );
	}

	public String getAreaDutos(Integer id) {
        String sql = "SELECT CAST(row_to_json(fc) AS TEXT) AS featurecollection "
        		+ "FROM ( SELECT 'FeatureCollection' As type, array_to_json( array_agg( f ) ) As features "
        		+ "FROM (SELECT 'Feature' As type, ST_AsGeoJSON( ST_Transform(area_atuacao_dutos,3857  ) )::json As geometry,"
        		+ "row_to_json((SELECT l FROM (SELECT id_empresa, nome_fantasia) As l)) As properties "
        		+ "FROM ( SELECT id_empresa, nome_fantasia, area_atuacao_dutos from tb_empresas where area_atuacao_dutos is not null and id_empresa = "+id+" ) As l ) As f) as fc;";		
		return doRequest( sql );
	}

	public String getAreaRodovias(Integer id) {
        String sql = "SELECT CAST(row_to_json(fc) AS TEXT) AS featurecollection "
        		+ "FROM ( SELECT 'FeatureCollection' As type, array_to_json( array_agg( f ) ) As features "
        		+ "FROM (SELECT 'Feature' As type, ST_AsGeoJSON( ST_Transform(area_atuacao_rodoviaria,3857  ) )::json As geometry,"
        		+ "row_to_json((SELECT l FROM (SELECT id_empresa, nome_fantasia) As l)) As properties "
        		+ "FROM ( SELECT id_empresa, nome_fantasia, area_atuacao_rodoviaria from tb_empresas where area_atuacao_dutos is not null and id_empresa = "+id+" ) As l ) As f) as fc;";		
		return doRequest( sql );
	}	
	
	
}
