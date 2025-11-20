package br.com.drwars.repositories;


import br.com.drwars.entites.Duto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutoRepository extends JpaRepository<Duto, Long> {

}