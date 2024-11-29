package models;

import java.sql.Date;

public class OS {
    private int id;
    private String placaVeiculo;
    private EnumStatus status;
    private Date dataInicio;
    private Date dataFim;
    private double valorTotal;
    private double valorPago;
    private String cidade;

    public OS() {
    }

    public OS(String placaVeiculo, EnumStatus status, Date dataInicio, Date dataFim, double valorTotal, double valorPago, String cidade) {
        this.placaVeiculo = placaVeiculo;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public String getCidade() {
        return cidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
