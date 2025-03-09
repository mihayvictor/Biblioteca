package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Usuario user;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataRealDevolucao;
    private LocalDate dataDevolucaoPrevista;
    private Integer dias;
    
    
    public Emprestimo(Usuario user, Livro livro, LocalDate dataEmprestimo, Integer dias, LocalDate dataRealDevolucao) {
        this.user = user;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dias = dias;
        this.dataRealDevolucao = dataRealDevolucao;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(dias);
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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
        return "Usuário: " + user + "\n" 
        + "Livro: " + livro + "\n" 
        + "Data de empréstimo: " + fmt.format(dataEmprestimo) + "\n" 
        + "Data prevista para a devolução: " + fmt.format(dataDevolucaoPrevista)+ "\n" 
        + "Data real de devolução: " + fmt.format(dataRealDevolucao);
    }

    

    

   
   

    

}
