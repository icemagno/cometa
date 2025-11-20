package br.com.drwars.repositories;

import br.com.drwars.entites.Contrato;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.RelatorioAnual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RelatorioAnualRepository extends JpaRepository<RelatorioAnual, Long> {


   @Query("SELECT e FROM RelatorioAnual e WHERE e.compra.id=:idcompra")
    List<RelatorioAnual> findByIdCompra(@Param("idcompra") Long idcompra);

}