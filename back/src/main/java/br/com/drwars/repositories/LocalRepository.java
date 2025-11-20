package br.com.drwars.repositories;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

}