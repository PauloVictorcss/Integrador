package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.PecaSubstituir;
import models.interfaces.IPecaSubstituirCRUD;

public class PecaSubstituirDAO implements IPecaSubstituirCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public PecaSubstituirDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(PecaSubstituir objServico) throws Exception{
        if (objServico.getIdPeca() < 0) throw new Exception("Id da peça é obrigatório");
        if (objServico.getIdOS() < 0) throw new Exception("Id da OS é obrigatório");
        if (objServico.getQuantidade() < 0) throw new Exception("Quantidade não pode ser negativa");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatório");
        if (objServico.getValorUnitario() < 0) throw new Exception("Valor unitário não pode ser negativo");
        
        objServico.setValorTotal(objServico.getValorUnitario() * objServico.getQuantidade());
        
        try {
            String sql =  "insert into Peca(idPeca, idOS, quantidade, descricao, valorUnitario, valorTotal)"
                    +     "values(?,?,?,?,?::money,?::money);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdPeca());
            preparedStatement.setInt(2, objServico.getIdOS());
            preparedStatement.setInt(3, objServico.getQuantidade());
            preparedStatement.setString(4,objServico.getDescricao());
            preparedStatement.setDouble(5, objServico.getValorUnitario());
            preparedStatement.setDouble(6, objServico.getValorTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    
    @Override
    public ArrayList<PecaSubstituir> obterListaDePecaSubstituir() throws Exception {
        ArrayList<PecaSubstituir> listaDePecasSubstituir = new ArrayList<>();
        String sql = "select * from PecaSubstituir order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                PecaSubstituir objPecasSubstituir = new PecaSubstituir();
                objPecasSubstituir.setId(rs.getInt("id"));
                objPecasSubstituir.setIdPeca(rs.getInt("idPeca"));
                objPecasSubstituir.setIdOS(rs.getInt("idOS"));
                objPecasSubstituir.setQuantidade(rs.getInt("quantidade"));
                objPecasSubstituir.setDescricao(rs.getString("descricao"));
                objPecasSubstituir.setValorUnitario(rs.getDouble("valorUnitario"));
                objPecasSubstituir.setValorTotal(rs.getDouble("valorTotal"));
                listaDePecasSubstituir.add(objPecasSubstituir);
            }
            return listaDePecasSubstituir;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    @Override
    public void editar(PecaSubstituir objServico) throws Exception {
        if (objServico.getIdPeca() < 0) throw new Exception("Id da peça é obrigatório");
        if (objServico.getIdOS() < 0) throw new Exception("Id da OS é obrigatório");
        if (objServico.getQuantidade() < 0) throw new Exception("Quantidade não pode ser negativa");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatório");
        if (objServico.getValorUnitario() < 0) throw new Exception("Valor unitário não pode ser negativo");
        
        objServico.setValorTotal(objServico.getValorUnitario() * objServico.getQuantidade());
        
        try {
            String sql = "UPDATE Peca SET idPeca = ?, idOS = ?, quantidade = ?, descricao = ?, valorUnitario= ?::money, valorUnitario= ?::Total WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdPeca());
            preparedStatement.setInt(2, objServico.getIdOS());
            preparedStatement.setInt(3, objServico.getQuantidade());
            preparedStatement.setString(4, objServico.getDescricao());
            preparedStatement.setDouble(5, objServico.getValorUnitario());
            preparedStatement.setDouble(6, objServico.getValorTotal());
            preparedStatement.setInt(7, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma peça encontrada com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar peça a ser substituída: " + erro.getMessage());
        }
    }
}
