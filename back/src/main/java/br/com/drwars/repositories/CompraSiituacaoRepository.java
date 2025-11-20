package br.com.drwars.repositories;

import br.com.drwars.entites.Compra;
import br.com.drwars.entites.CompraSituacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraSiituacaoRepository extends JpaRepository<CompraSituacao, Long> {

    
}