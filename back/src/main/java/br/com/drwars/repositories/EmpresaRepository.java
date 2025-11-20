package br.com.drwars.repositories;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
    Empresa findEmpresaByCnpj(String cnpj);

    @Query("SELECT e FROM Empresa e WHERE e.empresaTipo.id in (:tipo)")
    Page<Empresa> findByTipos(@Param("tipo") List<Long> tipo , Pageable pageable);

    @Query("SELECT e FROM Empresa e WHERE e.razaoSocial LIKE  %:razaoSocial% and e.empresaTipo.id in (:tipo)")
    Page<Empresa> findByRazaoSocialTipos(@Param("razaoSocial") String razaoSocial, @Param("tipo") List<Long> tipo , Pageable pageable);


    @Query("SELECT e FROM Empresa e WHERE e.id <> :id")
    Page<Empresa> findMenosEmpresa(@Param("id") Long id , Pageable pageable);
}