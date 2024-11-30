package models;

public class Peca {
    private int id;
    private String nome;
    private String fabricante;
    private String volumeTamanho;
    private int quantidadeEstoque;
    private double valorUnitario;

    public Peca() {
    }

    public Peca(String nome, String fabricante, String volumeTamanho, int quantidadeEstoque, double valorUnitario) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.volumeTamanho = volumeTamanho;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorUnitario = valorUnitario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setVolumeTamanho(String volumeTamanho) {
        this.volumeTamanho = volumeTamanho;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getVolumeTamanho() {
        return volumeTamanho;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }
}
