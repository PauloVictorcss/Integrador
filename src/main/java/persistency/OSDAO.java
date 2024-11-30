package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.EnumStatus;
import models.OS;
import models.interfaces.IOSCRUD;

public class OSDAO implements IOSCRUD {;
    //Conexao com o banco
    private Connection conexao = null;
    public OSDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(OS objServico) throws Exception {
        if (objServico.getStatus() == null || "".equals(objServico.getStatus().toString())) throw new Exception("Status é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veiculo é obrigatória");
        if (objServico.getDataInicio() == null) throw new Exception("Data de Início é obrigatório");
        if (objServico.getValorTotal() < 0) throw new Exception("Valor total não pode ser negativo");
        
        if (objServico.getValorPago() < 0) objServico.setValorPago(0.0);
        if (objServico.getCidade() == null || "".equals(objServico.getCidade())) objServico.setCidade("Goiânia");
        
        try {
            String sql =  "insert into OS(status, placaVeiculo, dataInicio, dataFim, valorTotal, valorPago, cidade)"
                    +     "values(?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getStatus().name());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.setDate(3, objServico.getDataInicio());
            preparedStatement.setDate(4, objServico.getDataFim());
            preparedStatement.setDouble(5, objServico.getValorTotal());
            preparedStatement.setDouble(6, objServico.getValorPago());
            preparedStatement.setString(7, objServico.getCidade());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }
    
    @Override
    public ArrayList<OS> obterListaDeOS() throws Exception {
        ArrayList<OS> listaDeOSs = new ArrayList<>();
        String sql = "select * from OS order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                OS objServico = new OS();
                objServico.setId(rs.getInt("id"));
                objServico.setStatus(EnumStatus.valueOf(rs.getString("status")));
                objServico.setPlacaVeiculo(rs.getString("placaVeiculo"));
                objServico.setDataInicio(rs.getDate("dataInicio"));
                objServico.setDataFim(rs.getDate("dataFim"));
                objServico.setValorTotal(rs.getDouble("valorTotal"));
                objServico.setValorPago(rs.getDouble("valorPago"));
                objServico.setCidade(rs.getString("cidade"));
                listaDeOSs.add(objServico);
            }
            return listaDeOSs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    @Override
    public OS buscarPorId(int id) throws Exception {
        OS os = null;
        String sql = "SELECT * FROM OS WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                os = new OS();
                os.setId(rs.getInt("id"));
                os.setStatus(EnumStatus.valueOf(rs.getString("status")));
                os.setPlacaVeiculo(rs.getString("placaVeiculo"));
                os.setDataInicio(rs.getDate("dataInicio"));
                os.setDataFim(rs.getDate("dataFim"));
                os.setValorTotal(rs.getDouble("valorTotal"));
                os.setValorPago(rs.getDouble("valorPago"));
                os.setCidade(rs.getString("cidade"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar OS por ID: " + e.getMessage());
        }

        return os;
    }
    
    @Override
    public ArrayList<OS> buscarPorStatus(EnumStatus status) throws Exception {
        ArrayList<OS> listaDeOSs = new ArrayList<>();
        String sql = "SELECT * FROM OS WHERE status = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, status.name());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OS os = new OS();
                os.setId(rs.getInt("id"));
                os.setStatus(EnumStatus.valueOf(rs.getString("status")));
                os.setPlacaVeiculo(rs.getString("placaVeiculo"));
                os.setDataInicio(rs.getDate("dataInicio"));
                os.setDataFim(rs.getDate("dataFim"));
                os.setValorTotal(rs.getDouble("valorTotal"));
                os.setValorPago(rs.getDouble("valorPago"));
                os.setCidade(rs.getString("cidade"));
                listaDeOSs.add(os);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar OS por Status: " + e.getMessage());
        }

        return listaDeOSs;
    }

    @Override
    public void editar(OS objServico) throws Exception {
        if (objServico.getStatus() == null || "".equals(objServico.getStatus().toString())) throw new Exception("Status é obrigatório");
        if (objServico.getPlacaVeiculo() == null || "".equals(objServico.getPlacaVeiculo())) throw new Exception("Placa do veiculo é obrigatória");
        if (objServico.getDataInicio() == null) throw new Exception("Data de Início é obrigatório");
        if (objServico.getValorTotal() < 0) throw new Exception("Valor total não pode ser negativo");
        
        if (objServico.getValorPago() < 0) objServico.setValorPago(0.0);
        if (objServico.getCidade() == null || "".equals(objServico.getCidade())) objServico.setCidade("Goiânia");
        
        try {
            String sql = "UPDATE OS SET status = ?, placaVeiculo = ?, dataInicio = ?, dataFim = ?, valorTotal= ?,"
                    + "valorPago= ?,  cidade = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getStatus().name());
            preparedStatement.setString(2, objServico.getPlacaVeiculo());
            preparedStatement.setDate(3, objServico.getDataInicio());
            preparedStatement.setDate(4, objServico.getDataFim());
            preparedStatement.setDouble(5, objServico.getValorTotal());
            preparedStatement.setDouble(6, objServico.getValorPago());
            preparedStatement.setString(7, objServico.getCidade());
            preparedStatement.setInt(8, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma OS encontrada com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar a OS: " + erro.getMessage());
        }
    }
}
