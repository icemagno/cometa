package br.com.drwars.repositories;

import br.com.drwars.entites.Autorizacao;
import br.com.drwars.entites.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {



}