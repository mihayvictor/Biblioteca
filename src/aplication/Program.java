package aplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Emprestimo;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.BibliotecaService;
import model.services.PlanoPadrao;
import utils.InputUtils;

public class Program {
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        Usuario usuario = new Usuario();
        List<Emprestimo> emprestimos = new ArrayList<>();
        BibliotecaService bibliotecaService = new BibliotecaService(new PlanoPadrao());
        
            String user = InputUtils.solicitarstring("Nome do usuário: ", scanner);
            int n = InputUtils.solicitarInt("Quantos livros quer cadastrar? ", scanner);
            while (n < 1) {
                System.out.println("Erro: impossível registrar menos de 1 livro.");
                n = InputUtils.solicitarInt("Quantos livros quer cadastrar? ", scanner);
            }

                double totalEmpProcessado = 0;

                for(int i = 0; i<n; i++){
                String titulo = InputUtils.solicitarstring("Qual o título do livro? ", scanner);
                LocalDate dataEmprestimo = InputUtils.solicitarData("Qual a data do emprestímo (dd/MM/yyyy)? ", scanner);
                int dias = InputUtils.solicitarInt("Qual o número de dias para a devlução? ", scanner);
                while (dias < 1) {
                    System.out.println("Erro: O número de dias para emprestimo de um livro não pode ser menor que 1.");
                    dias = InputUtils.solicitarInt("Qual o número de dias para a devolução? ", scanner);
                }
                LocalDate dataRealDevolucao = InputUtils.solicitarData("Qual a data real de devolução (dd/MM/yyyy)? ", scanner);
                
                usuario = new Usuario(user, emprestimos);
                Emprestimo emprestimo = new Emprestimo(new Livro(titulo), dataEmprestimo, dias, dataRealDevolucao);
                
                double empProcessado = bibliotecaService.processarEmprestimo(emprestimo);
                System.out.println("Livro: " + emprestimo.getLivro());
                System.out.printf("Multa por atraso: R$%.2f%n", empProcessado);
                totalEmpProcessado += empProcessado;
                usuario.getEmprestimo().add(emprestimo);
                }
                
                System.out.println("Empréstimo registrado: ");
                System.out.println(usuario.getNome());
                for (Emprestimo emp : usuario.getEmprestimo()) {
                    System.out.println(emp);
                }
                
                System.out.printf("Total da multa por atraso: R$%.2f%n", totalEmpProcessado);
                bibliotecaService.emitirNotaFiscal(usuario, usuario.getEmprestimo());
                
            scanner.close();
        
    }

}
