package com.example.desafioaula.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.desafioaula.model.Conta;
import com.example.desafioaula.model.PixDTO;
import com.example.desafioaula.model.ValorDTO;
import com.example.desafioaula.repository.ContaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @PostMapping
    public ResponseEntity<Conta> cadastrarConta(@Valid @RequestBody Conta conta) {
        Conta novaConta = repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> buscarTodasContas() {
        List<Conta> contas = repository.findAll();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> buscarContaPorCPF(@PathVariable String cpf) {
        Conta conta = repository.findByCpf(cpf);
        if (conta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
        }
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrarConta(@PathVariable Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
        conta.setStatusConta(false);
        repository.save(conta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<Conta> deposito(@PathVariable Long id, @RequestBody ValorDTO valorDTO) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
        conta.deposito(valorDTO.getValor());
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }

    @PutMapping("/{id}/saque")
    public ResponseEntity<Conta> saque(@PathVariable Long id, @RequestBody ValorDTO valorDTO) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
        conta.saque(valorDTO.getValor());
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }
    @PutMapping("/pix")
    public ResponseEntity<Void> pix(@Valid @RequestBody PixDTO pixDTO) {
    
        Conta contaOrigem = repository.findById(pixDTO.getIdContaOrigem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de origem não encontrada"));
    
        
        Conta contaDestino = repository.findById(pixDTO.getIdContaDestino())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de destino não encontrada"));
    
        
        BigDecimal valor = pixDTO.getValor();
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente na conta de origem");
        }
    
        
        contaOrigem.saque(valor);
    
        
        contaDestino.deposito(valor);
    
        // Salvar as alterações no banco de dados
        repository.save(contaOrigem);
        repository.save(contaDestino);
    
        return ResponseEntity.ok().build();
    }
    
}
