package br.com.drwars.repositories;

import br.com.drwars.entites.NegociacaoProposta;
import br.com.drwars.entites.NegociacaoRodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoPropostaRepository extends JpaRepository<NegociacaoProposta, Long> {

}