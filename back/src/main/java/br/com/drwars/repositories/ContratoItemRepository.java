package br.com.drwars.repositories;

import br.com.drwars.entites.ContratoItem;
import br.com.drwars.entites.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ContratoItemRepository extends JpaRepository<ContratoItem, Long> {

    @Query("SELECT c FROM ContratoItem c WHERE c.descricao LIKE  %:descricao%")
    ContratoItem findByDescricao(@Param("descricao") String descricao);

}