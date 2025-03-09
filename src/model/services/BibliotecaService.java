package model.services;

import java.time.Period;

import model.entities.Emprestimo;

public class BibliotecaService {

    private ServicoEmprestimo servicoEmprestimo;

    public BibliotecaService(ServicoEmprestimo servicoEmprestimo) {
        this.servicoEmprestimo = servicoEmprestimo;
    }

    public Double processarEmprestimo(Emprestimo emprestimo){
        Integer diasAtraso=0;
        if (emprestimo.getDataRealDevolucao().isAfter(emprestimo.getDataDevolucaoPrevista())) {
           diasAtraso = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getDays(); 
        }
        return servicoEmprestimo.calcularMulta(diasAtraso); 
    }



    



}
