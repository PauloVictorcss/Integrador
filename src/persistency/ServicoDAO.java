package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Servico;
import models.interfaces.IServicoCRUD;

public class ServicoDAO implements IServicoCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public ServicoDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Servico objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descricao é obrigatória");
        if (objServico.getValorUnitario() <= 0) throw new Exception("Valor unitário deve ser positivo e diferente de zero");
        
        try {
            String sql =  "insert into Servico(nome, descricao, valorUnitario)"
                    +     "values(?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.setDouble(3, objServico.getValorUnitario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<Servico> obterListaDeServicos() throws Exception {
        ArrayList<Servico> listaDeServicos = new ArrayList<>();
        String sql = "select * from Servico order by nomeServico";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Servico objServicos = new Servico();
                objServicos.setId(rs.getInt("id"));
                objServicos.setNome(rs.getString("nome"));
                objServicos.setDescricao(rs.getString("descricao"));
                objServicos.setValorUnitario(rs.getDouble("valorUnitario"));
                listaDeServicos.add(objServicos);
            }
            return listaDeServicos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
  
    @Override
    public ArrayList<String> obterListaDeNomesServicos() throws Exception {
        ArrayList<String> listaDeNomes = new ArrayList<>();
        String sql = "SELECT nome FROM Servico ORDER BY nome";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String nome = rs.getString("nome");
                listaDeNomes.add(nome);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter lista de nomes de serviços: " + e.getMessage());
        }

        return listaDeNomes;
    }

    @Override
    public Servico buscaPorId(int id) throws Exception {
        String sql = "SELECT * FROM Servico WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Servico objServico = new Servico();
                objServico.setId(rs.getInt("id"));
                objServico.setNome(rs.getString("nome"));
                objServico.setDescricao(rs.getString("descricao"));
                objServico.setValorUnitario(rs.getDouble("valorUnitario"));
                return objServico;
            } else {
                throw new Exception("Serviço não encontrado com o id: " + id);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar serviço por id: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Servico> buscaPorNome(String nome) throws Exception {
        ArrayList<Servico> listaDeServicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico WHERE nome LIKE ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Servico objServico = new Servico();
                objServico.setId(rs.getInt("id"));
                objServico.setNome(rs.getString("nome"));
                objServico.setDescricao(rs.getString("descricao"));
                objServico.setValorUnitario(rs.getDouble("valorUnitario"));
                listaDeServicos.add(objServico);
            }

            if (listaDeServicos.isEmpty()) {
                throw new Exception("Nenhum serviço encontrado com o nome: " + nome);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar serviços por nome: " + e.getMessage());
        }

        return listaDeServicos;
    }

    @Override
    public void editar(Servico objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descricao é obrigatória");
        if (objServico.getValorUnitario() <= 0) throw new Exception("Valor unitário deve ser positivo e diferente de zero");
        
        try {
            String sql = "UPDATE Servico SET nome = ?, descricao = ?, valorUnitario = ? WHERE nomeServico = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.setDouble(3, objServico.getValorUnitario());
            preparedStatement.setInt(4, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma Servico encontrado com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar Servico: " + erro.getMessage());
        }
    }
}
