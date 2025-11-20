package br.com.drwars.repositories;

import br.com.drwars.entites.NegociacaoItens;
import br.com.drwars.entites.NegociacaoRodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoItensRepository extends JpaRepository<NegociacaoItens, Long> {

}