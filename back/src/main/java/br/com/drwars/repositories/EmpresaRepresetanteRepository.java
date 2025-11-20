package br.com.drwars.repositories;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.EmpresaRepresetantes;
import br.com.drwars.entites.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepresetanteRepository extends JpaRepository<EmpresaRepresetantes, Long> {

    @Query("SELECT e FROM EmpresaRepresetantes e WHERE e.representante.id =:idrepresetante and e.empresa.id =:idempresa")
    EmpresaRepresetantes findByIdRepresetanteAndIdEmpresa(@Param("idrepresetante") Long idrepresetante,@Param("idempresa") Long idempresa);
}