package models;

import java.sql.Date;

public class ServicoExecutado {
    private int id;
    private int idOS;
    private int idServico;
    private int funcionarioId;
    private Date dataInicio;
    private Date dataFim;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private String descriccao;

    public ServicoExecutado() {
    }

    public ServicoExecutado(int idOS, int idServico, int funcionarioId, Date dataInicio, Date dataFim, int quantidade, double valorUnitario, double valorTotal, String descriccao) {
        this.idOS = idOS;
        this.idServico = idServico;
        this.funcionarioId = funcionarioId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.descriccao = descriccao;
    }

    public int getId() {
        return id;
    }

    public int getIdOS() {
        return idOS;
    }

    public int getIdServico() {
        return idServico;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getDescriccao() {
        return descriccao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDescriccao(String descriccao) {
        this.descriccao = descriccao;
    }
}
