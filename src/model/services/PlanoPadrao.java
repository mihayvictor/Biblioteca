package model.services;
public class PlanoPadrao implements ServicoEmprestimo {

    @Override
    public Double calcularDiasAtrasado(int diasAtrasado) {
        return diasAtrasado * 2.00;
    }

    @Override
    public Double calcularMesesAtrasado(int mesesAtrasado) {
        return mesesAtrasado * 100.00;
    }

    @Override
    public Double calcularAnosAtrasado(int anosAtrasado) {
        return anosAtrasado * 1500.00;
    }



}
