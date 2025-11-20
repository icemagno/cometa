package br.com.drwars.repositories;

import br.com.drwars.entites.NoticiaSituacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaSituacaoRepository extends JpaRepository<NoticiaSituacao, Long> {

    
}