package models;

public class PessoaFisica {
    private int idCliente;
    private String cpf;

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
}

