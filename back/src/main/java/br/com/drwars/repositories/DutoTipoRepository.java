package br.com.drwars.repositories;


import br.com.drwars.entites.DutoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DutoTipoRepository extends JpaRepository<DutoTipo, Long> {

    @Query("SELECT d FROM DutoTipo d WHERE d.sigla=:sigla ")
    DutoTipo findBySigla(@Param("sigla") String sigla);
}