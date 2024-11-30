package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.VeiculoAcessorio;
import models.interfaces.IVeiculoAcessorioCRUD;

public class VeiculoAcessorioDAO implements IVeiculoAcessorioCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public VeiculoAcessorioDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(VeiculoAcessorio objServico) throws Exception {
        if (objServico.getNomeAcessorio() == null || "".equals(objServico.getNomeAcessorio())) throw new Exception("Acessório é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veículo é obrigatória");
        
        try {
            String sql =  "insert into VeiculoAcessorio(nomeAcessorio, placaVeiculo)"
                    +     "values(?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNomeAcessorio());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }

    @Override
    public ArrayList<VeiculoAcessorio> obterListaDeVeiculoAcessorio() throws Exception {
        ArrayList<VeiculoAcessorio> listaDeVeiculoAcessorio = new ArrayList<>();
        String sql = "select * from VeiculoAcessorio order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                VeiculoAcessorio objVeiculoAcessorio = new VeiculoAcessorio();
                objVeiculoAcessorio.setId(rs.getInt("id"));
                objVeiculoAcessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                objVeiculoAcessorio.setPlacaVeiculo(rs.getString("placaVeiculo"));
                listaDeVeiculoAcessorio.add(objVeiculoAcessorio);
            }
            return listaDeVeiculoAcessorio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public VeiculoAcessorio buscarPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido");

        VeiculoAcessorio acessorio = null;
        String sql = "SELECT * FROM VeiculoAcessorio WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                acessorio = new VeiculoAcessorio();
                acessorio.setId(rs.getInt("id"));
                acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                acessorio.setPlacaVeiculo(rs.getString("placaVeiculo"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar VeiculoAcessorio por id: " + e.getMessage());
        }

        return acessorio;
    }

    
    @Override
    public ArrayList<VeiculoAcessorio> buscarPorNomeAcessorioNoVeiculo(String nome) throws Exception {
        if (nome == null || "".equals(nome))  throw new Exception("Nome do acessório é obrigatório");

        ArrayList<VeiculoAcessorio> listaDeAcessorios = new ArrayList<>();
        String sql = "SELECT * FROM VeiculoAcessorio WHERE nomeAcessorio = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                VeiculoAcessorio acessorio = new VeiculoAcessorio();
                acessorio.setId(rs.getInt("id"));
                acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                acessorio.setPlacaVeiculo(rs.getString("placaVeiculo"));
                listaDeAcessorios.add(acessorio);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar VeiculoAcessorio por nomeAcessorio: " + e.getMessage());
        }

        return listaDeAcessorios.isEmpty() ? null : listaDeAcessorios;
    }
    
    @Override
    public ArrayList<VeiculoAcessorio> buscarPorPlacaVeiculo(String placa) throws Exception {
        if (placa == null || "".equals(placa))  throw new Exception("Placa do veículo é obrigatória");

        ArrayList<VeiculoAcessorio> listaDeAcessorios = new ArrayList<>();
        String sql = "SELECT * FROM VeiculoAcessorio WHERE placaVeiculo = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                VeiculoAcessorio acessorio = new VeiculoAcessorio();
                acessorio.setId(rs.getInt("id"));
                acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                acessorio.setPlacaVeiculo(rs.getString("placaVeiculo"));
                listaDeAcessorios.add(acessorio);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar VeiculoAcessorio por placaVeiculo: " + e.getMessage());
        }

        return listaDeAcessorios.isEmpty() ? null : listaDeAcessorios;
    }

    @Override
    public void editar(VeiculoAcessorio objServico) throws Exception {
        if (objServico.getNomeAcessorio() == null || "".equals(objServico.getNomeAcessorio())) throw new Exception("Acessório é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veículo é obrigatória");
        
        try {
            String sql = "UPDATE VeiculoAcessorio SET nomeAcessorio = ?, placaVeiculo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNomeAcessorio());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.setInt(3, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhum Veiculo_Acessorio encontrado com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o Veiculo_Acessorio: " + erro.getMessage());
        }
    }
}
