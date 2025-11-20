package br.com.drwars.repositories;

import br.com.drwars.entites.Compra;
import br.com.drwars.entites.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    @Query("SELECT c FROM Contrato c WHERE   c.compra.id=:idCompra ")
    List<Contrato> findByCompra(@Param("idCompra") Long idCompra);

}