package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataRealDevolucao;
    private LocalDate dataDevolucaoPrevista;
    private Integer dias;
    
    public Emprestimo(Livro livro, LocalDate dataEmprestimo, Integer dias, LocalDate dataRealDevolucao) {
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dias = dias;
        this.dataRealDevolucao = dataRealDevolucao;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(dias);
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
        this.dataRealDevolucao = dataRealDevolucao;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        return "Livro: " + livro + "\n" 
        + "Data de empréstimo: " + fmt.format(dataEmprestimo) + "\n" 
        + "Data prevista para a devolução: " + fmt.format(dataDevolucaoPrevista)+ "\n" 
        + "Data real de devolução: " + fmt.format(dataRealDevolucao);
    }

    

    

   
   

    

}
