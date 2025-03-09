package aplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Emprestimo;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.BibliotecaService;
import model.services.PlanoPadrao;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do usuário: ");
        String user = sc.nextLine();
        System.out.print("Título do livro: ");
        String titulo = sc.nextLine();
        System.out.print("Data do empréstimo: (dd/MM/yyyy): ");
        LocalDate dataEmprestimo = LocalDate.parse(sc.next(), fmt);
        System.out.print("Número de dias para a devolução: ");
        sc.nextLine();
        int dias = sc.nextInt();
        System.out.print("Data real de devolução (dd/MM/yyyy): ");
        sc.nextLine();
        LocalDate dataRealDevolucao = LocalDate.parse(sc.next(), fmt);

        Emprestimo emprestimo = new Emprestimo(new Usuario(user), new Livro(titulo), dataEmprestimo, dias, dataRealDevolucao);
        BibliotecaService bibliotecaService = new BibliotecaService(new PlanoPadrao());

        double empProcessado = bibliotecaService.processarEmprestimo(emprestimo);

        System.out.println("Empréstimo registrado: ");
        System.out.println(emprestimo);
        System.out.printf("Multa por atraso: R$%.2f", empProcessado);

       
        




    }

}
