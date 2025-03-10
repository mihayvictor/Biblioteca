package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    List<Emprestimo> emprestimo = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario() {
        //TODO Auto-generated constructor stub
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
        return emprestimo;
    }


    

}
