package com.example.desafioaula.validation;

import com.example.desafioaula.model.TipoMovimentacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoMovimentacaoValidator implements ConstraintValidator<TipoMovimentacaoConstraint, TipoMovimentacao> {

    @Override
    public boolean isValid(TipoMovimentacao value, ConstraintValidatorContext context) {
        return value != null;
    }
}
