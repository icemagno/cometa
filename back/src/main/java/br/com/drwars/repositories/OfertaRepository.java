package br.com.drwars.repositories;

import br.com.drwars.entites.Oferta;
import br.com.drwars.entites.enums.OfertaTipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    @Query("SELECT o FROM Oferta o WHERE o.tipo in (:tipo) and o.ofertaTipo.id in (:idTipoOferta)  and  (o.empresa.id=:idEmpresa or o.empresaPrivada.id=:idEmpresa or o.empresaPrivada.id is null )")
    Page<Oferta> findOfetaByTipoOferta(@Param("tipo") List<OfertaTipoEnum> tipo, @Param("idTipoOferta") List<Long> idTipoOferta, @Param("idEmpresa") Long idEmpresa, Pageable pageable);

    @Query("SELECT o FROM Oferta o WHERE o.ofertaSituacao.id in (:situacoes) and o.tipo in (:tipo) and o.ofertaTipo.id in (:idTipoOferta)  and  (o.empresa.id=:idEmpresa or o.empresaPrivada.id=:idEmpresa or o.empresaPrivada.id is null )")
    Page<Oferta> findOfetaByTipoOfertaSituacao(@Param("situacoes") List<Long> situacoes,@Param("tipo") List<OfertaTipoEnum> tipo, @Param("idTipoOferta") List<Long> idTipoOferta, @Param("idEmpresa") Long idEmpresa, Pageable pageable);

    @Query(value = "select sum(quantidade_ofertada) from tb_ofertas where id_empresa=:idEmpresa ", nativeQuery = true)
    Long findOfetaByTipoOfertaSituacao(@Param("idEmpresa") Long idEmpresa);

}