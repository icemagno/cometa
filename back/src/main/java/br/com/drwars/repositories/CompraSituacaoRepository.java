package br.com.drwars.repositories;

import br.com.drwars.entites.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraSituacaoRepository extends JpaRepository<Compra, Long> {

    
}