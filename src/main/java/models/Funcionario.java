package models;

import java.sql.Date;

public class Funcionario {
    private int id;
    private String cpf;
    private String nome;
    private Date dataEntrada;
    private Date dataSaida;
    private int telefone;
    private String email;

    public Funcionario() {}

    public Funcionario(String cpf, String nome, Date dataEntrada, Date dataSaida, int telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.telefone = telefone;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }    
}
