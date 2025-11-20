package br.com.drwars.repositories;


import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {


    @Query("SELECT m FROM Municipio m WHERE m.uf LIKE  %:uf%")
    List<Municipio> findByUf(@Param("uf") String uf);

    @Query(value = "select st_union(geom) from ibge_municipio mun inner join ibge_municipio_regiao_geom geo on mun.codigo = cast(geo.cd_geocodm as integer) where mun.codigo in :municipios ", nativeQuery = true)
    String findGeom(@Param("municipios") List<Long> municipios);

}