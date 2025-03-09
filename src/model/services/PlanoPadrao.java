package model.services;

import java.time.LocalDate;
import java.time.Period;

import model.entities.Emprestimo;

public class PlanoPadrao implements ServicoEmprestimo {

    @Override
    public Double calcularMulta(int diasAtrasado) {
        return diasAtrasado * 2.00;
    }


}
