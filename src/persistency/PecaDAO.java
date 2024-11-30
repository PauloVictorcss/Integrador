package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Peca;
import models.interfaces.IPecaCRUD;

public class PecaDAO implements IPecaCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public PecaDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Peca objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getFabricante() == null || "".equals(objServico.getFabricante())) throw new Exception("Fabricante é obrigatório");
        if (objServico.getQuantidadeEstoque() < 0) throw new Exception("Quantidade em estoque não pode ser negativa");
        if (objServico.getValorUnitario() <= 0) throw new Exception("Valor unitário deve ser positivo e diferente de zero");
        
        try {
            String sql =  "insert into Peca(nome, fabricante, volumeTamanho, quantidadeEstoque, valorUnitario)"
                    +     "values(?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getFabricante());
            preparedStatement.setString(3, objServico.getVolumeTamanho());
            preparedStatement.setInt(4,objServico.getQuantidadeEstoque());
            preparedStatement.setDouble(5, objServico.getValorUnitario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<Peca> obterListaDePecas() throws Exception {
        ArrayList<Peca> listaDePecas = new ArrayList<>();
        String sql = "select * from Peca order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Peca objPecas = new Peca();
                objPecas.setId(rs.getInt("id"));
                objPecas.setNome(rs.getString("nome"));
                objPecas.setFabricante(rs.getString("fabricante"));
                objPecas.setVolumeTamanho(rs.getString("volumeTamanho"));
                objPecas.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                objPecas.setValorUnitario(rs.getDouble("valorUnitario"));
                listaDePecas.add(objPecas);
            }
            return listaDePecas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public ArrayList<String> obterListaDePecasPorNome() throws Exception {
        ArrayList<String> listaDeNomes = new ArrayList<>();
        String sql = "SELECT nome FROM Peca ORDER BY nome";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String nome = rs.getString("nome");
                listaDeNomes.add(nome);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter lista de peças: " + e.getMessage());
        }

        return listaDeNomes;
    }
    
    @Override
    public Peca buscaPorId(int id) throws Exception {
        Peca peca = null;
        String sql = "SELECT * FROM Peca WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                peca = new Peca();
                peca.setId(rs.getInt("id"));
                peca.setNome(rs.getString("nome"));
                peca.setFabricante(rs.getString("fabricante"));
                peca.setVolumeTamanho(rs.getString("volumeTamanho"));
                peca.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                peca.setValorUnitario(rs.getDouble("valorUnitario"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Peça por ID: " + e.getMessage());
        }

        return peca;
    }
    
    @Override
    public ArrayList<Peca> buscaPorNome(String nome) throws Exception {
        ArrayList<Peca> listaDePecas = new ArrayList<>();
        if (nome == null || nome.isEmpty()) throw new Exception("Nome é obrigatório");

        String sql = "SELECT * FROM Peca WHERE nome LIKE ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Peca peca = new Peca();
                peca.setId(rs.getInt("id"));
                peca.setNome(rs.getString("nome"));
                peca.setFabricante(rs.getString("fabricante"));
                peca.setVolumeTamanho(rs.getString("volumeTamanho"));
                peca.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                peca.setValorUnitario(rs.getDouble("valorUnitario"));
                listaDePecas.add(peca);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Peça por nome: " + e.getMessage());
        }

        return listaDePecas;
    }

    @Override
    public void editar(Peca objServico) throws Exception {
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getFabricante() == null || "".equals(objServico.getFabricante())) throw new Exception("Fabricante é obrigatório");
        if (objServico.getQuantidadeEstoque() < 0) throw new Exception("Quantidade em estoque não pode ser negativa");
        if (objServico.getValorUnitario() <= 0) throw new Exception("Valor unitário deve ser positivo e diferente de zero");
        
        try {
            String sql = "UPDATE Peca SET nome = ?, fabricante = ?, volumeTamanho = ?, quantidadeEstoque = ?, valorUnitario= ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNome());
            preparedStatement.setString(2, objServico.getFabricante());
            preparedStatement.setString(3, objServico.getVolumeTamanho());
            preparedStatement.setInt(4, objServico.getQuantidadeEstoque());
            preparedStatement.setDouble(5, objServico.getValorUnitario());
            preparedStatement.setInt(6, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma peça encontrada com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar peça: " + erro.getMessage());
        }
    }
}
