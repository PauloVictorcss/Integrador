package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Marca;
import models.interfaces.IMarcaCRUD;

public class MarcaDAO implements IMarcaCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public MarcaDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Marca objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        
        try {
            String sql =  "insert into Marca(nome, descricao)"
                    +     "values(?,?);";
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
    public ArrayList<Marca> obterListaDeMarcas() throws Exception {
        ArrayList<Marca> listaDeMarcas = new ArrayList<>();
        String sql = "select * from Marca order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Marca objMarcas = new Marca();
                objMarcas.setId(rs.getInt("id"));
                objMarcas.setNome(rs.getString("nome"));
                objMarcas.setDescricao(rs.getString("descricao"));
                listaDeMarcas.add(objMarcas);
            }
            return listaDeMarcas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }

    @Override
    public void editar(Marca objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        
        try {
            String sql = "UPDATE Marca SET nome = ?, descricao = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.setInt(3, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma Marca encontrada com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar marca: " + erro.getMessage());
        }
    }
}
