package models;

import java.sql.Date;

public class PessoaFisica {
    private int idCliente;
    private String cpf;
    private Date dataDeNascimento;

    public PessoaFisica() {}

    public PessoaFisica(int idCliente, String cpf) {
        this.idCliente = idCliente;
        this.cpf = cpf;
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }
}

