package models;

public class VeiculoAcessorio {
    private int id;
    private String placaVeiculo;
    private String nomeAcessorio;

    public VeiculoAcessorio() {}

    public VeiculoAcessorio(String placaVeiculo, String nomeAcessorio) {
        this.placaVeiculo = placaVeiculo;
        this.nomeAcessorio = nomeAcessorio;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public String getPlacaVeiculo () {
        return placaVeiculo;
    }
    
    public void setPlacaVeiculo (String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }
    
    public String getNomeAcessorio() {
        return nomeAcessorio;
    }
    
    public void setNomeAcessorio (String nomeAcessorio) {
        this.nomeAcessorio = nomeAcessorio;
    }
}
