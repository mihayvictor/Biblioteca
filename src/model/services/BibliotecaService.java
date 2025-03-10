package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void gerarBoleto() throws IOException{
        String path = "/home/mihay96/nota.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
    }


    



}
