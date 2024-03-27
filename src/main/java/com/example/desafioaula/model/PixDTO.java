package com.example.desafioaula.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class PixDTO {

    @NotNull(message = "O ID da conta de origem não pode ser nulo")
    private Long idContaOrigem;

    @NotNull(message = "O ID da conta de destino não pode ser nulo")
    private Long idContaDestino;

    @Positive(message = "O valor da transação Pix deve ser positivo")
    private BigDecimal valor;

    private LocalDate dataPix;

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(Long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(Long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataPix() {
        return dataPix;
    }

    public void setDataPix(LocalDate dataPix) {
        this.dataPix = dataPix;
    }
}
