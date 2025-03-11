package aplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Emprestimo;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.BibliotecaService;
import model.services.PlanoPadrao;

public class Program {
    public static void main(String[] args) throws IOException {
        
        
        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        
        double totalEmpProcessado = 0.0;
        double empProcessado = 0.0;
        
        Usuario usuario = new Usuario();
        List<Emprestimo> emprestimos = new ArrayList<>();
        BibliotecaService bibliotecaService = new BibliotecaService(new PlanoPadrao());
        
        System.out.print("Nome do usuário: ");
        String user = sc.nextLine();
        
        System.out.print("Quantos Livros deseja cadastrar?: ");
        int n = sc.nextInt();
        for(int i = 0; i<n; i++){
        System.out.print("Título do livro: ");
        sc.nextLine();
        String titulo = sc.nextLine();
        System.out.print("Data do empréstimo: (dd/MM/yyyy): ");
        LocalDate dataEmprestimo = LocalDate.parse(sc.next(), fmt);
        System.out.print("Número de dias para a devolução: ");
        sc.nextLine();
        int dias = sc.nextInt();
        System.out.print("Data real de devolução (dd/MM/yyyy): ");
        sc.nextLine();
        LocalDate dataRealDevolucao = LocalDate.parse(sc.next(), fmt);
        
        usuario = new Usuario(user, emprestimos);
        Emprestimo emprestimo = new Emprestimo(new Livro(titulo), dataEmprestimo, dias, dataRealDevolucao);
        
        empProcessado = bibliotecaService.processarEmprestimo(emprestimo);
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
        
        sc.close();

    }

}
