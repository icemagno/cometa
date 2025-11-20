package br.com.drwars.repositories;

import br.com.drwars.entites.EmpresaSituacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaSituacaoRepository extends JpaRepository<EmpresaSituacao, Long> {

}