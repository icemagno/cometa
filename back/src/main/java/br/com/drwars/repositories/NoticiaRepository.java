package br.com.drwars.repositories;

import br.com.drwars.entites.Noticia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    @Query("SELECT n FROM Noticia n ")
    Page<Noticia> findNoticiaPaginada(Pageable pageable);
    
    @Query("SELECT n FROM Noticia n where n.situacao.id=:idSituacao ")
    Page<Noticia> findNoticiaPorTipoPaginada(Pageable pageable,@Param("idSituacao") Long idSituacao);
}