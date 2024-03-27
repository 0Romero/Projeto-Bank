package com.example.desafioaula.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoMovimentacaoApplication implements ConstraintValidator<TipoMovimentacao, TipoMovimentacao> {

    @Override
    public boolean isValid(TipoMovimentacao value, ConstraintValidatorContext context) {
        return value == TipoMovimentacao.DEPOSITO || value == TipoMovimentacao.SAQUE || value == TipoMovimentacao.PIX;
    }

}