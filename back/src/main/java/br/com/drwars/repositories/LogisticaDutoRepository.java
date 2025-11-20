package br.com.drwars.repositories;


import br.com.drwars.entites.LogisticaDuto;
import br.com.drwars.entites.Oferta;
import br.com.drwars.entites.enums.OfertaTipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticaDutoRepository extends JpaRepository<LogisticaDuto, Long> {

    @Query("SELECT l FROM LogisticaDuto l WHERE  l.empresa.id=:idEmpresa ")
    Page<LogisticaDuto> findAllByEmpresa(@Param("idEmpresa") Long idEmpresa, Pageable pageable);

}