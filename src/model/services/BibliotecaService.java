package model.services;

import java.time.Period;

import model.entities.Emprestimo;

public class BibliotecaService {

    private ServicoEmprestimo servicoEmprestimo;
    Double multa;
    Integer diasAtraso=0;
    

    public BibliotecaService(ServicoEmprestimo servicoEmprestimo) {
        this.servicoEmprestimo = servicoEmprestimo;
    }

    public void processarEmprestimo(Emprestimo emprestimo){
        if (emprestimo.getDataRealDevolucao().isAfter(emprestimo.getDataDevolucaoPrevista())) {
           diasAtraso = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getDays(); 
        }
        multa = servicoEmprestimo.calcularMulta(diasAtraso); 
    }

    public Double getMulta() {
        return multa;
    }

    public Integer getDiasAtraso() {
        return diasAtraso;
    }


    



}
