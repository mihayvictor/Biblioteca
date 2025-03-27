package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

import model.entities.Emprestimo;
import model.entities.Usuario;
import utils.InputUtils;

public class BibliotecaService {

    private Scanner scanner = new Scanner(System.in);
    private ServicoEmprestimo servicoEmprestimo;

    public BibliotecaService(ServicoEmprestimo servicoEmprestimo) {
        this.servicoEmprestimo = servicoEmprestimo;
    }

    public Double processarEmprestimo(Emprestimo emprestimo){
        Integer diasAtraso=0; 
        int meses = 0;
        boolean validarData = false;

        do{
         meses = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getMonths();
        
            if (emprestimo.getDataRealDevolucao().isBefore(emprestimo.getDataEmprestimo())) {
                System.out.println("A data de real da devolução não pode ser anterior a data do emprestímo! ");
                emprestimo.setDataEmprestimo(InputUtils.solicitarData("Qual a data do emprestimo (dd/MM/yyyy)? ", scanner));
                emprestimo.setDataRealDevolucao(InputUtils.solicitarData("qual a data real de devolução (dd/MM/yyyy)? ", scanner));
                continue;
            }
            validarData = true;
        
        }while (validarData == false);
        
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
        scanner.close();
        
    }


    



}
