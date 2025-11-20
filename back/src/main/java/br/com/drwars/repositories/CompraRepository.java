package br.com.drwars.repositories;

import br.com.drwars.entites.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT c FROM Compra c WHERE   c.empresa.id=:idEmpresa or c.empresaFornecedora.id=:idEmpresa  ")
    Page<Compra> findByEmpresa(@Param("idEmpresa") Long idEmpresa, Pageable pageable);

    @Query("SELECT c FROM Compra c WHERE   c.oferta.id=:idOferta   ")
    List<Compra> findByOferta(@Param("idOferta") Long idOferta);
}