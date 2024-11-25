package models;

public class Modelo {
    private int id;
    private String nome;
    private String descricao;
    private int idMarca;

    public Modelo() {}

    public Modelo(String nome, String descricao, int idMarca) {
        this.nome = nome;
        this.descricao = descricao;
        this.idMarca = idMarca;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDecricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getIdMarca() {
        return idMarca;
    }
    
    public void setIdMarca (int idMarca) {
        this.idMarca = idMarca;
    }
}
