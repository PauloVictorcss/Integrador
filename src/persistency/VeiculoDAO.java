package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Veiculo;
import models.interfaces.IVeiculoCRUD;

public class VeiculoDAO implements IVeiculoCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public VeiculoDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Veiculo objServico) throws Exception {
        if (objServico.getPlaca() == null || "".equals(objServico.getPlaca())) throw new Exception("Placa é obrigatória");
        if (contarDigitos(objServico.getIdModelo()) < 1 || objServico.getIdModelo() <= 0) throw new Exception("Modelo é obrigatório");
        if (objServico.getChassi() == null || "".equals(objServico.getChassi())) throw new Exception("Chassi é obrigatório");
        if (objServico.getRenavan() == null || "".equals(objServico.getRenavan())) throw new Exception("Renavan é obrigatório");
        if (contarDigitos(objServico.getAnoFabricacao()) != 4 || objServico.getAnoFabricacao() < 1886 || objServico.getAnoFabricacao() < 2025)
            throw new Exception("Ano de fabricação é obrigatório");
        if (contarDigitos(objServico.getAnoModelo()) != 4 || objServico.getAnoModelo() < 1886 || objServico.getAnoModelo() < 2025)
            throw new Exception("Ano do modelo é obrigatório");
        if (contarDigitos(objServico.getQuilometragem()) < 1) throw new Exception("Quilometragem é obrigatória");
        
        try {
            String sql =  "insert into Veiculo(placa, idModelo, chassi, renavan, anoFabricacao, anoModelo, identificadorPatrimonio, quilometragem)"
                    +     "values(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getPlaca());
            preparedStatement.setInt(2, objServico.getIdModelo());
            preparedStatement.setString(3, objServico.getChassi());
            preparedStatement.setString(4,objServico.getRenavan());
            preparedStatement.setInt(5, objServico.getAnoFabricacao());
            preparedStatement.setInt(6, objServico.getAnoModelo());
            preparedStatement.setString(7, objServico.getIdentificadorPatrimonio());
            preparedStatement.setInt(8, objServico.getQuilometragem());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<Veiculo> obterListaDeVeiculos() throws Exception {
        ArrayList<Veiculo> listaDeVeiculos = new ArrayList<>();
        String sql = "select * from Veiculo order by placa";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Veiculo objVeiculos = new Veiculo();
                objVeiculos.setPlaca(rs.getString("placa"));
                objVeiculos.setIdModelo(rs.getInt("idModelo"));
                objVeiculos.setChassi(rs.getString("chassi"));
                objVeiculos.setRenavan(rs.getString("renavan"));
                objVeiculos.setAnoFabricacao(rs.getInt("anoFabricacao"));
                objVeiculos.setAnoModelo(rs.getInt("anoModelo"));
                objVeiculos.setIdentificadorPatrimonio(rs.getString("identificadorPatrimonio"));
                objVeiculos.setQuilometragem(rs.getInt("quilometragem"));
                listaDeVeiculos.add(objVeiculos);
            }
            return listaDeVeiculos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public ArrayList<String> obterListaDePlacasVeiculos() throws Exception {
        ArrayList<String> listaDePlacas = new ArrayList<>();
        String sql = "SELECT placa FROM Veiculo ORDER BY placa";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String placa = rs.getString("placa");
                listaDePlacas.add(placa);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter lista de placas de veículos: " + e.getMessage());
        }

        return listaDePlacas;  // Retorna a lista de placas
    }
    
    @Override
    public Veiculo buscaPorPlaca(String placa) throws Exception {
        if (placa == null || "".equals(placa)) throw new Exception("Placa é obrigatória");

        Veiculo veiculo = null;
        String sql = "SELECT * FROM Veiculo WHERE placa = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setIdModelo(rs.getInt("idModelo"));
                veiculo.setChassi(rs.getString("chassi"));
                veiculo.setRenavan(rs.getString("renavan"));
                veiculo.setAnoFabricacao(rs.getInt("anoFabricacao"));
                veiculo.setAnoModelo(rs.getInt("anoModelo"));
                veiculo.setIdentificadorPatrimonio(rs.getString("identificadorPatrimonio"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Veículo por placa: " + e.getMessage());
        }

        return veiculo;  // Retorna o veículo encontrado ou null
    }
  
    @Override
    public void editar(Veiculo objServico) throws Exception {
        if (objServico.getPlaca() == null || "".equals(objServico.getPlaca())) throw new Exception("Placa é obrigatória");
        if (contarDigitos(objServico.getIdModelo()) < 1 || objServico.getIdModelo() <= 0) throw new Exception("Modelo é obrigatório");
        if (objServico.getChassi() == null || "".equals(objServico.getChassi())) throw new Exception("Chassi é obrigatório");
        if (objServico.getRenavan() == null || "".equals(objServico.getRenavan())) throw new Exception("Renavan é obrigatório");
        if (contarDigitos(objServico.getAnoFabricacao()) != 4 || objServico.getAnoFabricacao() < 1886 || objServico.getAnoFabricacao() < 2025)
            throw new Exception("Ano de fabricação é obrigatório");
        if (contarDigitos(objServico.getAnoModelo()) != 4 || objServico.getAnoModelo() < 1886 || objServico.getAnoModelo() < 2025)
            throw new Exception("Ano do modelo é obrigatório");
        if (contarDigitos(objServico.getQuilometragem()) < 1) throw new Exception("Quilometragem é obrigatória");
        
        try {
            String sql = "UPDATE Veiculo SET idModelo = ?, chassi = ?, renavan = ?, anoFabricacao = ?, "
                       + "anoModelo = ?, identificadorPatrimonio = ?, quilometragem = ? WHERE placa = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdModelo());
            preparedStatement.setString(2, objServico.getChassi());
            preparedStatement.setString(3, objServico.getRenavan());
            preparedStatement.setInt(4, objServico.getAnoFabricacao());
            preparedStatement.setInt(5, objServico.getAnoModelo());
            preparedStatement.setString(6, objServico.getIdentificadorPatrimonio());
            preparedStatement.setInt(7, objServico.getQuilometragem());
            preparedStatement.setString(8, objServico.getPlaca());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhum Veiculo encontrado com a placa fornecida: " + objServico.getPlaca());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o veiculo: " + erro.getMessage());
        }
    }
}
