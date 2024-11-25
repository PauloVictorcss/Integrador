package models;

import java.sql.Date;

public class Propriedade {
    private int id;
    private int idCliente;
    private String placaVeiculo;
    private Date datainicio;
    private Date dataTermino;

    public Propriedade() {
    }

    public Propriedade(int idCliente, String placaVeiculo, Date datainicio, Date dataTermino) {
        this.idCliente = idCliente;
        this.placaVeiculo = placaVeiculo;
        this.datainicio = datainicio;
        this.dataTermino = dataTermino;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
}
