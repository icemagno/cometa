package br.com.drwars.repositories;

import br.com.drwars.entites.EntregaSazonais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaSazonaisRepository extends JpaRepository<EntregaSazonais, Long> {

}