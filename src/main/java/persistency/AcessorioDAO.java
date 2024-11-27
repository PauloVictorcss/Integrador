package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Acessorio;
import models.interfaces.IAcessorioCRUD;

public class AcessorioDAO implements IAcessorioCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public AcessorioDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Acessorio objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        
        try {
            String sql =  "insert into Acessorios(nome, descricao) values(?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    
    @Override
    public ArrayList<Acessorio> obterListaDeAcessorios() throws Exception {
        ArrayList<Acessorio> listaDeAcessorios = new ArrayList<>();
        String sql = "select * from Acessorios order by nome";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Acessorio objAcessorios = new Acessorio();
                objAcessorios.setNome(rs.getString("nome"));
                objAcessorios.setDescricao(rs.getString("descricao"));
                listaDeAcessorios.add(objAcessorios);
            }
            return listaDeAcessorios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void editar(Acessorio objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        
        try {
            String sql = "UPDATE Acessorios SET descricao = ? WHERE nome = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getDescricao());
            preparedStatement.setString(2, objServico.getNome());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhum acessorio encontrado com o nome fornecido: " + objServico.getNome());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o acessorio: " + erro.getMessage());
        }
    }
}
