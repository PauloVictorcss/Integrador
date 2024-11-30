package models;

public class PessoaJuridica {
    private int idCliente;
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String nomeResponsavel;
    private int ddiContatoResponsavel;
    private int dddContatoResponsavel;
    private int contatoResponsavel;

    public PessoaJuridica() {
    }

    public PessoaJuridica(int idCliente, String cnpj, String razaoSocial, String inscricaoEstadual, String nomeResponsavel, int ddiContatoResponsavel, int dddContatoResponsavel, int contatoResponsavel) {
        this.idCliente = idCliente;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
        this.nomeResponsavel = nomeResponsavel;
        this.ddiContatoResponsavel = ddiContatoResponsavel;
        this.dddContatoResponsavel = dddContatoResponsavel;
        this.contatoResponsavel = contatoResponsavel;
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public int getDdiContatoResponsavel() {
        return ddiContatoResponsavel;
    }

    public void setDdiContatoResponsavel(int ddiContatoResponsavel) {
        this.ddiContatoResponsavel = ddiContatoResponsavel;
    }

    public int getDddContatoResponsavel() {
        return dddContatoResponsavel;
    }

    public void setDddContatoResponsavel(int dddContatoResponsavel) {
        this.dddContatoResponsavel = dddContatoResponsavel;
    }

    public int getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(int contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }
}
