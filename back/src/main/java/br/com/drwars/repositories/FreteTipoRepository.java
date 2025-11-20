package br.com.drwars.repositories;

import br.com.drwars.entites.FreteTipo;
import br.com.drwars.entites.Perfil;
import br.com.drwars.entites.Representante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteTipoRepository extends JpaRepository<FreteTipo, Long> {

    FreteTipo findFreteTipoBySigla(String sigla);


}