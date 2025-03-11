package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, List<Emprestimo> emprestimos) {
        this.nome = nome;
        this.emprestimos = emprestimos;
    }

    public Usuario() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public List<Emprestimo> getEmprestimo() {
        return emprestimos;
    }


    

}
