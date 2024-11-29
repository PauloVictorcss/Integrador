package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.ServicoExecutado;
import models.interfaces.IServicoExecutadoCRUD;

public class ServicoExecutadoDAO implements IServicoExecutadoCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public ServicoExecutadoDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(ServicoExecutado objServico) throws Exception {
        if (objServico.getIdServico() <= 0 || contarDigitos(objServico.getIdServico ()) < 1) throw new Exception("Id do serviço é obrigatório");
        if (objServico.getIdOS() <= 0 || contarDigitos(objServico.getIdOS ()) < 1) throw new Exception("Id da OS é obrigatório");
        if (objServico.getDataInicio() == null) throw new Exception("Data de Início é obrigatória");
        if (objServico.getQuantidade() < 0) throw new Exception("Quantidade não pode ser negativa");
        if (objServico.getValorUnitario() < 0) throw new Exception("Valor unitário não pode ser negativo");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        if (objServico.getFuncionarioId() < 0) throw new Exception("Id do funcionário é obrigatório");
        
        objServico.setValorTotal(objServico.getValorUnitario() * objServico.getQuantidade());
        
        try {
            String sql =  "insert into ServicoExecutado(idOS, idServico, dataInicio, dataFim, quantidade, valorUnitario, valorTotal, funcionarioId, descricao)"
                    +     "values(?,?,?,?,?,?::money,?::money,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdOS());
            preparedStatement.setInt(2, objServico.getIdServico());
            preparedStatement.setDate(3, objServico.getDataInicio());
            preparedStatement.setDate(4, objServico.getDataFim());
            preparedStatement.setInt(5, objServico.getQuantidade());
            preparedStatement.setDouble(6, objServico.getValorUnitario());
            preparedStatement.setDouble(7, objServico.getValorTotal());
            preparedStatement.setInt(8, objServico.getFuncionarioId());
            preparedStatement.setString(9, objServico.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    
    @Override
    public ArrayList<ServicoExecutado> obterListaDeServicosExecutados() throws Exception {
        ArrayList<ServicoExecutado> listaDeServicosExecutados = new ArrayList<>();
        String sql = "select * from ServicoExecutado order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                ServicoExecutado objPecasSubstituir = new ServicoExecutado();
                objPecasSubstituir.setId(rs.getInt("id"));
                objPecasSubstituir.setIdOS(rs.getInt("idOS"));
                objPecasSubstituir.setIdServico(rs.getInt("idServico"));
                objPecasSubstituir.setDataInicio(rs.getDate("dataInicio"));
                objPecasSubstituir.setDataFim(rs.getDate("dataFim"));
                objPecasSubstituir.setQuantidade(rs.getInt("quantidade"));
                objPecasSubstituir.setValorUnitario(rs.getDouble("valorUnitario"));
                objPecasSubstituir.setValorTotal(rs.getDouble("valorTotal"));
                objPecasSubstituir.setFuncionarioId(rs.getInt("funcionarioId"));
                objPecasSubstituir.setDescricao(rs.getString("descricao"));
                listaDeServicosExecutados.add(objPecasSubstituir);
            }
            return listaDeServicosExecutados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    @Override
    public void editar(ServicoExecutado objServico) throws Exception {
        if (objServico.getIdServico() < 0) throw new Exception("Id do serviço é obrigatório");
        if (objServico.getIdOS() < 0) throw new Exception("Id da OS é obrigatório");
        if (objServico.getDataInicio() == null) throw new Exception("Data de Início é obrigatória");
        if (objServico.getQuantidade() < 0) throw new Exception("Quantidade não pode ser negativa");
        if (objServico.getValorUnitario() < 0) throw new Exception("Valor unitário não pode ser negativo");
        if (objServico.getDescricao() == null || "".equals(objServico.getDescricao())) throw new Exception("Descrição é obrigatória");
        if (objServico.getFuncionarioId() < 0) throw new Exception("Id do funcionário é obrigatório");
        
        objServico.setValorTotal(objServico.getValorUnitario() * objServico.getQuantidade());
        
        try {
            String sql = "UPDATE ServicoExecutado SET idOS = ?, idServico = ?, dataInicio = ?, dataFim = ?, quantidade = ?, valorUnitario= ?::money,"
                    + "valorUnitario= ?::Total,  funcionarioId = ?, descricao = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdOS());
            preparedStatement.setInt(2, objServico.getIdServico());
            preparedStatement.setDate(3, objServico.getDataInicio());
            preparedStatement.setDate(4, objServico.getDataFim());
            preparedStatement.setInt(5, objServico.getQuantidade());
            preparedStatement.setDouble(6, objServico.getValorUnitario());
            preparedStatement.setDouble(7, objServico.getValorTotal());
            preparedStatement.setInt(8, objServico.getFuncionarioId());
            preparedStatement.setString(9, objServico.getDescricao());
            preparedStatement.setInt(10, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma serviço executado encontrado com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o serviço executado: " + erro.getMessage());
        }
    }
}
