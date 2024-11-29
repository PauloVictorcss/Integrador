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
