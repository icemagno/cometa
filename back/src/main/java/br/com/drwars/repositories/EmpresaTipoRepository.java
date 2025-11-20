package br.com.drwars.repositories;

import br.com.drwars.entites.EmpresaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaTipoRepository extends JpaRepository<EmpresaTipo, Long> {

}