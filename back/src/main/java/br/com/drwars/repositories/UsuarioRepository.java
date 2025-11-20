package br.com.drwars.repositories;

import br.com.drwars.entites.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.userName =:userName")
    Usuario findByUsername(@Param("userName") String userName);

    @Query("SELECT u FROM Usuario u WHERE u.email =:email")
    List<Usuario> findByEmail(@Param("email") String email);


    @Query("SELECT u FROM Usuario u WHERE u.userName LIKE  %:userName%")
    Page<Usuario> findByUsername(@Param("userName") String userName, Pageable pageable);


    @Query("SELECT u FROM Usuario u WHERE u.empresa.id =:idEmpresa")
    List<Usuario> findByEmpresa(@Param("idEmpresa") Long idEmpresa);


    @Query("SELECT u FROM Usuario u WHERE u.userName =:userName and u.password=:senha")
    Usuario validaSenha(@Param("userName") String userName, @Param("senha") String senha);
}