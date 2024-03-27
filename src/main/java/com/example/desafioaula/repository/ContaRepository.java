package com.example.desafioaula.repository;

import com.example.desafioaula.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByCpf(String cpf);
}
