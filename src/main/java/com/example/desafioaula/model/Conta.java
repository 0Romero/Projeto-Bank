package com.example.desafioaula.model;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{account.name.NotBlank}")
    @Size(min = 3, max = 255, message = "{account.name.size}")
    private String name;

    @NotBlank(message = "{account.cpf.NotBlank}")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{account.cpf.Patern}")
    private String cpf;

    @NotBlank(message = "{account.accountNumber.NotBlank}")
    @Size(min = 3, max = 255, message = "{account.accountNumber.size}")
    private String accountNumber;

    @NotBlank(message = "{account.agencia.NotBlank}")
    @Size(min = 3, max = 255, message = "account.agencia.Size")
    private String agencia;

    private LocalDate dataAbertura;

    @NotNull(message = "{account.statusConta.NotNull}")
    private boolean statusConta;

    @NotBlank(message = "Tipo de Conta Necessaria, sendo Poupança , Corrente")
    private String tipoConta;

    private BigDecimal saldo; // Adiciona o campo de saldo

    public void deposito(BigDecimal valor) {
        this.saldo = this.saldo.add(valor); // Adiciona o valor ao saldo
    }

    public void saque(BigDecimal valor) {
        if (this.saldo.compareTo(valor) >= 0) { // Verifica se há saldo suficiente para o saque
            this.saldo = this.saldo.subtract(valor); // Subtrai o valor do saldo
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar o saque"); // Lança uma exceção se o saldo for insuficiente
        }
    }

    public BigDecimal getSaldo() {
        return this.saldo; // Retorna o saldo da conta
    }
}
