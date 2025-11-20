package br.com.drwars.repositories;

import br.com.drwars.entites.OfertaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaTipoRepository extends JpaRepository<OfertaTipo, Long> {

    
}