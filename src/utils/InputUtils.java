package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String solicitarstring(String mensagem, Scanner scanner){
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static int solicitarInt(String mensagem, Scanner scanner){
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número intereiro.");
            }
            
        }
    }

    public static double solicitarDouble(String mensagem, Scanner scanner){
        while (true) {
            try {
                System.out.print(mensagem);
            return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número decimal.");
            }
        }
    }

    public static LocalDate solicitarData(String mensagem, Scanner scanner){
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDate.parse(scanner.nextLine(), fmt);
            } catch (DateTimeParseException e) {
               System.out.println("Entrada inválida! Digite uma data no formato dd/MM/yyyy ");
            }
            
        }
    }



}
