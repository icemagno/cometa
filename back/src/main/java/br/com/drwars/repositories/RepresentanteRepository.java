package br.com.drwars.repositories;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    Representante findRepresentanteByCpf(String cpf);
}