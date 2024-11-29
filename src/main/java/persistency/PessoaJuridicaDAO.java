package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import static Tools.Validacoes.validarCPF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.PessoaJuridica;
import models.interfaces.IPessoaJuridicaCRUD;

public class PessoaJuridicaDAO implements IPessoaJuridicaCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public PessoaJuridicaDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(PessoaJuridica objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do cliente é obrigatório");
        if (objServico.getCnpj() == null || !validarCPF(objServico.getCnpj())) throw new Exception("CNPJ é obrigatório");
        if (objServico.getRazaoSocial() == null || "".equals(objServico.getRazaoSocial())) throw new Exception("Razao Social é obrigatória");
        if (objServico.getInscricaoEstadual() == null || "".equals(objServico.getInscricaoEstadual())) throw new Exception("Inscrição Estadual é obrigatória");
        if (objServico.getNomeResponsavel() == null || "".equals(objServico.getNomeResponsavel())) throw new Exception("Nome do Responsável é obrigatório");
        if (contarDigitos(objServico.getDdiContatoResponsavel()) != 2 || objServico.getDdiContatoResponsavel() == 00)
            throw new Exception("DDI é obrigatório");
        if (contarDigitos(objServico.getDddContatoResponsavel()) != 2 || objServico.getDddContatoResponsavel() == 00)
            throw new Exception("DDD é obrigatório");
        if (contarDigitos(objServico.getContatoResponsavel()) < 8) throw new Exception("Numero do telefone é obrigatório");
        
        try {
            String sql =  "insert into PessoaJuridica(idCliente, cnpj, razaoSocial, inscricaoEstadual, nomeResponsavel, "
                       +  "ddiContatoResponsavel, dddContatoResponsavel, contatoResponsavel) values(?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdCliente());
            preparedStatement.setString(2, objServico.getCnpj());
            preparedStatement.setString(3, objServico.getRazaoSocial());
            preparedStatement.setString(4, objServico.getInscricaoEstadual());
            preparedStatement.setString(5, objServico.getNomeResponsavel());
            preparedStatement.setInt(6, objServico.getDdiContatoResponsavel());
            preparedStatement.setInt(7, objServico.getDddContatoResponsavel());
            preparedStatement.setInt(8, objServico.getContatoResponsavel());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<PessoaJuridica> obterPessoasJuridica() throws Exception {
        ArrayList<PessoaJuridica> listaPessoaJuridica = new ArrayList<>();
        String sql = "select * from PessoaJuridica order by idCliente";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                PessoaJuridica objPessoaFisisca = new PessoaJuridica();
                objPessoaFisisca.setIdCliente(rs.getInt("idCliente"));
                objPessoaFisisca.setCnpj(rs.getString("cnpj"));
                objPessoaFisisca.setRazaoSocial(rs.getString("razaoSocial"));
                objPessoaFisisca.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                objPessoaFisisca.setNomeResponsavel(rs.getString("nomeResponsavel"));
                objPessoaFisisca.setDdiContatoResponsavel(rs.getInt("ddiContatoResponsavel"));
                objPessoaFisisca.setDddContatoResponsavel(rs.getInt("dddContatoResponsavel"));
                objPessoaFisisca.setContatoResponsavel(rs.getInt("contatoResponsavel"));
                listaPessoaJuridica.add(objPessoaFisisca);
            }
            return listaPessoaJuridica;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
  
    @Override
    public void editar(PessoaJuridica objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do cliente é obrigatório");
        if (objServico.getCnpj() == null || !validarCPF(objServico.getCnpj())) throw new Exception("CNPJ é obrigatório");
        if (objServico.getRazaoSocial() == null || "".equals(objServico.getRazaoSocial())) throw new Exception("Razao Social é obrigatória");
        if (objServico.getInscricaoEstadual() == null || "".equals(objServico.getInscricaoEstadual())) throw new Exception("Inscrição Estadual é obrigatória");
        if (objServico.getNomeResponsavel() == null || "".equals(objServico.getNomeResponsavel())) throw new Exception("Nome do Responsável é obrigatório");
        if (contarDigitos(objServico.getDdiContatoResponsavel()) != 2 || objServico.getDdiContatoResponsavel() == 00)
            throw new Exception("DDI é obrigatório");
        if (contarDigitos(objServico.getDddContatoResponsavel()) != 2 || objServico.getDddContatoResponsavel() == 00)
            throw new Exception("DDD é obrigatório");
        if (contarDigitos(objServico.getContatoResponsavel()) < 8) throw new Exception("Numero do telefone é obrigatório");
        
        try {
            String sql = "UPDATE PessoaJuridica SET cnpj = ?, razaoSocial = ?, inscricaoEstadual= ?, nomeResponsavel = ?, "
                       + " ddiContatoResponsavel = ?, dddContatoResponsavel = ?, contatoResponsavel = ? WHERE idCliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getCnpj());
            preparedStatement.setString(2, objServico.getRazaoSocial());
            preparedStatement.setString(3, objServico.getInscricaoEstadual());
            preparedStatement.setString(4, objServico.getNomeResponsavel());
            preparedStatement.setInt(5, objServico.getDdiContatoResponsavel());
            preparedStatement.setInt(6, objServico.getDddContatoResponsavel());
            preparedStatement.setInt(7, objServico.getContatoResponsavel());
            preparedStatement.setInt(8, objServico.getIdCliente());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma pessoa jurídica encontrada com o id fornecido: " + objServico.getIdCliente());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar a pessoa jurídica: " + erro.getMessage());
        }
    }
}
