package models;

public class Veiculo {
    private String placa;
    private int idModelo;
    private String chassi;
    private String renavan;
    private int anoFabricacao;
    private int anoModelo;
    private int quilometragem;
    private String identificadorPatrimonio;

    public Veiculo() {
    }

    public Veiculo(String placa, int idModelo, String chassi, String renavan, int anoFabricacao, int anoModelo, int quilometragem, String identificadorPatrimonio) {
        this.placa = placa;
        this.idModelo = idModelo;
        this.chassi = chassi;
        this.renavan = renavan;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.quilometragem = quilometragem;
        this.identificadorPatrimonio = identificadorPatrimonio;
    }

    public String getPlaca() {
        return placa;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public String getChassi() {
        return chassi;
    }

    public String getRenavan() {
        return renavan;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public String getIdentificadorPatrimonio() {
        return identificadorPatrimonio;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void setIdentificadorPatrimonio(String identificadorPatrimonio) {
        this.identificadorPatrimonio = identificadorPatrimonio;
    }
}
