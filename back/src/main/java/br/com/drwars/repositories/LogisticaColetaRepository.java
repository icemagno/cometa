package br.com.drwars.repositories;

import br.com.drwars.entites.LogisticaColeta;
import br.com.drwars.entites.LogisticaDuto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticaColetaRepository extends JpaRepository<LogisticaColeta, Long> {

    @Query("SELECT l FROM LogisticaColeta l WHERE  l.empresa.id=:idEmpresa ")
    Page<LogisticaColeta> findAllByEmpresa(@Param("idEmpresa") Long idEmpresa, Pageable pageable);

}