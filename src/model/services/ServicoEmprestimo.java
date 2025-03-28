package model.services;
public interface ServicoEmprestimo {

    Double calcularDiasAtrasado(int diasAtrasado);

    Double calcularMesesAtrasado(int mesesAtrasado);

    Double calcularAnosAtrasado(int anosAtrasado);
}
