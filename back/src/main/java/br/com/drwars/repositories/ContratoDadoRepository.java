package br.com.drwars.repositories;

import br.com.drwars.entites.Compra;
import br.com.drwars.entites.ContratoDado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContratoDadoRepository extends JpaRepository<ContratoDado, Long> {


    @Query("SELECT c FROM ContratoDado c WHERE   c.contrato.id=:idContrato ")
    List<ContratoDado> findByidContrato(@Param("idContrato") Long idContrato);

}