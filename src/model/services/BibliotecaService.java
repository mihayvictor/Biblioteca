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

    private int diasAtraso; 
    private int meses;
    private int anos;
    private double totalAtrasoPorLivro;

    private Scanner scanner = new Scanner(System.in);
    private ServicoEmprestimo servicoEmprestimo;

    public BibliotecaService(ServicoEmprestimo servicoEmprestimo) {
        this.servicoEmprestimo = servicoEmprestimo;
    }
 
    public void validarData(Emprestimo emprestimo){
        boolean validarData = false;

        while (validarData == false) {
            //Valida se a data de devolução não antes da data de empréstimo
            if (emprestimo.getDataRealDevolucao().isBefore(emprestimo.getDataEmprestimo())) {

                System.out.println("A data real da devolução não pode ser anterior a data do empréstimo! ");
                emprestimo.setDataEmprestimo(InputUtils.solicitarData("Qual a data do emprestimo (dd/MM/yyyy)? ", scanner));
                int dias = InputUtils.solicitarInt("Qual o número de dias para a devlução? ", scanner);
                emprestimo.setDataDevolucaoPrevista(emprestimo.getDataEmprestimo().plusDays(dias));
                emprestimo.setDataRealDevolucao(InputUtils.solicitarData("qual a data real de devolução (dd/MM/yyyy)? ", scanner));
                continue;
            }

            //calcula os anos de atrasados 
            anos = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getYears();
            //Calcula os meses atrasados
            meses = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getMonths();
            //calcula os dias atrasados
            diasAtraso = Period.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataRealDevolucao()).getDays(); 

            validarData = true;
        }
    }

    //Processa o empréstimo com base no tempo de atraso
    public Double processarEmprestimo(Emprestimo emprestimo){
       
        validarData(emprestimo);

        totalAtrasoPorLivro = servicoEmprestimo.calcularAnosAtrasado(anos);

        totalAtrasoPorLivro += servicoEmprestimo.calcularMesesAtrasado(meses);

        totalAtrasoPorLivro += servicoEmprestimo.calcularDiasAtrasado(diasAtraso);
        
        return totalAtrasoPorLivro;
    }

    //Gera nota fiscal
    public void emitirNotaFiscal(Usuario usuario, List<Emprestimo> emprestimos) throws IOException{
        String path = "/home/mihay96/nota.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        double totalMulta = 0;
        bw.write("Nome do usuário: " + usuario.getNome());
        bw.newLine();
        
        for (Emprestimo emp : emprestimos) {
            bw.newLine();
            bw.write(String.valueOf(emp));
            bw.newLine();
            bw.write(String.format("Valor da multa: %.2f", processarEmprestimo(emp)));
            totalMulta += processarEmprestimo(emp);
            bw.newLine();
        }

        bw.newLine();
        bw.write(String.format("Total da multa por atraso: R$%.2f%n", totalMulta));
        System.out.println("Nota Fiscal gerada com sucesso!!!");

        bw.close();
        scanner.close();
        
    }


    



}
