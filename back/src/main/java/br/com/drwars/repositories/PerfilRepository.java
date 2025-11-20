package br.com.drwars.repositories;

import br.com.drwars.entites.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("SELECT p FROM Perfil p WHERE p.nome LIKE  %:nome%")
    Page<Perfil> findPerfilByNome(@Param("nome") String nome, Pageable pageable);

    Perfil findByNome(@Param("nome") String nome);

}