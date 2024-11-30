package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Modelo;
import models.interfaces.IModeloCRUD;

public class ModeloDAO implements IModeloCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public ModeloDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Modelo objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        if (objServico.getIdMarca() <= 0 || contarDigitos(objServico.getIdMarca ()) < 1) throw new Exception("Id da Marca é obrigatório");
        
        try {
            String sql =  "insert into Modelo(nome, descricao, idMarca)"
                    +     "values(?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.setInt(3, objServico.getIdMarca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }

    @Override
    public ArrayList<Modelo> obterListaDeModelos() throws Exception {
        ArrayList<Modelo> listaDeModelos = new ArrayList<>();
        String sql = "select * from Modelo order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Modelo objModelos = new Modelo();
                objModelos.setId(rs.getInt("id"));
                objModelos.setNome(rs.getString("nome"));
                objModelos.setDescricao(rs.getString("descricao"));
                objModelos.setIdMarca(rs.getInt("idMarca"));
                listaDeModelos.add(objModelos);
            }
            return listaDeModelos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public Modelo buscaPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido");

        Modelo modelo = null;
        String sql = "SELECT * FROM Modelo WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Modelo por ID: " + e.getMessage());
        }

        return modelo;  // Retorna a marca encontrada ou null
    }
    
    @Override
    public ArrayList<Modelo> buscaPorNome(String nome) throws Exception {
        if (nome == null || "".equals(nome)) throw new Exception("Nome é obrigatório");

        ArrayList<Modelo> listaDeModelos = new ArrayList<>();
        String sql = "SELECT * FROM Modelo WHERE nome LIKE ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setDescricao(rs.getString("descricao"));
                listaDeModelos.add(modelo);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Modelo por nome: " + e.getMessage());
        }

        return listaDeModelos;
    }

    @Override
    public void editar(Modelo objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        if (objServico.getIdMarca() <= 0 || contarDigitos(objServico.getIdMarca ()) < 1) throw new Exception("Id da Marca é obrigatório");
        
        try {
            String sql = "UPDATE Modelo SET nome = ?, descricao = ?, idMarca = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getDescricao());
            preparedStatement.setInt(3, objServico.getIdMarca());
            preparedStatement.setInt(4, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhum Modelo encontrado com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o modelo: " + erro.getMessage());
        }
    }
}
