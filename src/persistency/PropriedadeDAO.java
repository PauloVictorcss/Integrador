package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Propriedade;
import models.interfaces.IPropriedadeCRUD;

public class PropriedadeDAO implements IPropriedadeCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public PropriedadeDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Propriedade objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do Cliente é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veículo é obrigatório");
        if (objServico.getDatainicio() == null) throw new Exception("Data de Início é obrigatória");
        
        try {
            String sql =  "insert into Propriedade(idCliente, placaVeiculo, dataInicio, dataTermino values(?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdCliente());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.setDate(3, objServico.getDatainicio());
            preparedStatement.setDate(4, objServico.getDataTermino());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    
    @Override
    public ArrayList<Propriedade> obterListaDePropriedades() throws Exception {
        ArrayList<Propriedade> listaDePropriedade = new ArrayList<>();
        String sql = "select * from Propriedade order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Propriedade objPropriedade = new Propriedade();
                objPropriedade.setId(rs.getInt("id"));
                objPropriedade.setIdCliente(rs.getInt("idCliente"));
                objPropriedade.setPlacaVeiculo(rs.getString("placaVeiculo"));
                objPropriedade.setDatainicio(rs.getDate("dataInicio"));
                objPropriedade.setDataTermino(rs.getDate("dataTermino"));
                listaDePropriedade.add(objPropriedade);
            }
            return listaDePropriedade;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    @Override
    public Propriedade buscarPorId(int id) throws Exception {
        Propriedade propriedade = null;
        String sql = "SELECT * FROM Propriedade WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                propriedade = new Propriedade();
                propriedade.setId(rs.getInt("id"));
                propriedade.setIdCliente(rs.getInt("idCliente"));
                propriedade.setPlacaVeiculo(rs.getString("placaVeiculo"));
                propriedade.setDatainicio(rs.getDate("dataInicio"));
                propriedade.setDataTermino(rs.getDate("dataTermino"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Propriedade por ID: " + e.getMessage());
        }

        return propriedade;
    }

    @Override
    public ArrayList<Propriedade> buscarPorIdCliente(int idCliente) throws Exception {
        ArrayList<Propriedade> listaDePropriedades = new ArrayList<>();
        String sql = "SELECT * FROM Propriedade WHERE idCliente = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Propriedade propriedade = new Propriedade();
                propriedade.setId(rs.getInt("id"));
                propriedade.setIdCliente(rs.getInt("idCliente"));
                propriedade.setPlacaVeiculo(rs.getString("placaVeiculo"));
                propriedade.setDatainicio(rs.getDate("dataInicio"));
                propriedade.setDataTermino(rs.getDate("dataTermino"));
                listaDePropriedades.add(propriedade);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Propriedade por ID Cliente: " + e.getMessage());
        }

        return listaDePropriedades;
    }

    @Override
    public ArrayList<Propriedade> buscarPorPlacaVeiculo(String placaVeiculo) throws Exception {
        ArrayList<Propriedade> listaDePropriedades = new ArrayList<>();
        String sql = "SELECT * FROM Propriedade WHERE placaVeiculo = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, placaVeiculo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Propriedade propriedade = new Propriedade();
                propriedade.setId(rs.getInt("id"));
                propriedade.setIdCliente(rs.getInt("idCliente"));
                propriedade.setPlacaVeiculo(rs.getString("placaVeiculo"));
                propriedade.setDatainicio(rs.getDate("dataInicio"));
                propriedade.setDataTermino(rs.getDate("dataTermino"));
                listaDePropriedades.add(propriedade);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Propriedade por Placa Veículo: " + e.getMessage());
        }

        return listaDePropriedades;
    }

    @Override
    public void editar(Propriedade objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do Cliente é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veículo é obrigatório");
        if (objServico.getDatainicio() == null) throw new Exception("Data de Início é obrigatória");
        
        try {
            String sql = "UPDATE Propriedade SET idCliente = ?, placaVeiculo = ?, dataInicio = ?, dataTermino = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdCliente());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.setDate(3, objServico.getDatainicio());
            preparedStatement.setDate(4, objServico.getDataTermino());
            preparedStatement.setInt(5, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma Propriedade encontrada com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar a propriedade: " + erro.getMessage());
        }
    }
}
