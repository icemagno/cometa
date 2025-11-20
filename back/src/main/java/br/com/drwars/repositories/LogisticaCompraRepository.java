package br.com.drwars.repositories;

import br.com.drwars.entites.LogisticaColeta;
import br.com.drwars.entites.LogisticaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticaCompraRepository extends JpaRepository<LogisticaCompra, Long> {


}