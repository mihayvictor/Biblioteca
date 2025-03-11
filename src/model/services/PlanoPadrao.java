package model.services;
public class PlanoPadrao implements ServicoEmprestimo {

    @Override
    public Double calcularMulta(int diasAtrasado) {
        return diasAtrasado * 2.00;
    }


}
