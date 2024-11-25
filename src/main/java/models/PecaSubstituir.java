package models;

public class PecaSubstituir {
    private int id;
    private int idOS;
    private int idPeca;
    private String descricao;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    public PecaSubstituir() {
    }

    public PecaSubstituir(int idOS, int idPeca, String descricao, int quantidade, double valorUnitario, double valorTotal) {
        this.idOS = idOS;
        this.idPeca = idPeca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public int getIdOS() {
        return idOS;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public String getDescricao() {
        return descricao;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
