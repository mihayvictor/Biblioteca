package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Period;
import java.util.List;

import model.entities.Emprestimo;
import model.entities.Usuario;

public class BibliotecaService {

    private ServicoEmprestimo servicoEmprestimo;

    public BibliotecaService(ServicoEmprestimo servicoEmprestimo) {
        this.servicoEmprestimo = servicoEmprestimo;
    }

    public Double processarEmprestimo(Emprestimo emprestimo){
        Integer diasAtraso=0;
        int meses = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getMonths();
        if ( meses > 0) {
            return (double) meses * 100;
        }   
        else if (emprestimo.getDataRealDevolucao().isAfter(emprestimo.getDataDevolucaoPrevista())) {
           diasAtraso = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getDays(); 
        }
         return servicoEmprestimo.calcularMulta(diasAtraso);
    }

    public void emitirNotaFiscal(Usuario usuario, List<Emprestimo> emprestimos) throws IOException{
        String path = "/home/mihay96/nota.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        double totalMulta = 0;
        bw.write("Usuário: " + usuario.getNome());
        bw.newLine();
        
        for (Emprestimo emp : emprestimos) {
            bw.newLine();
            bw.write(String.valueOf(emp));
            bw.newLine();
            bw.write(String.format("Valor da multa: %.2f", processarEmprestimo(emp)));
            totalMulta += processarEmprestimo(emp);
            bw.newLine();
        }
        
        bw.write(String.format("Total da multa por atraso: R$%.2f%n", totalMulta));
        System.out.println("Nota Fiscal gerada com sucesso!!!");
        bw.close();
        
    }


    



}
