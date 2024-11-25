package models;

public class Oficina {
    private String nomeDaOficina;
    private String email;
    private int ddi;
    private int ddd;
    private int numeroTelefone;
    private String logradouro;
    private int numeroEndereco;
    private String complemento;
    private String setor;
    private String cidade;

    public Oficina() {}

    public Oficina(String nomeDaOficina, String email, int ddi, int ddd, int numeroTelefone, String logradouro, int numeroEndereco, String complemento, String setor, String cidade) {
        this.nomeDaOficina = nomeDaOficina;
        this.email = email;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.logradouro = logradouro;
        this.numeroEndereco = numeroEndereco;
        this.complemento = complemento;
        this.setor = setor;
        this.cidade = cidade;
    }

    // Getters e Setters
    public String getNomeDaOficina() {
        return nomeDaOficina;
    }

    public void setNomeDaOficina(String nomeDaOficina) {
        this.nomeDaOficina = nomeDaOficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(int numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
