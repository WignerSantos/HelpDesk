package com.wigner.helpdesk.repositories;

import com.wigner.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> readByCpf(String cpf);

    Optional<Pessoa> readByEmail(String email);

}
