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
import models.PessoaFisica;
import models.interfaces.IPessoaFisicaCRUD;

public class PessoaFisicaDAO implements IPessoaFisicaCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public PessoaFisicaDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(PessoaFisica objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do cliente é obrigatório");
        if (objServico.getCpf() == null || !validarCPF(objServico.getCpf())) throw new Exception("CPF é obrigatório");
        if (objServico.getDataDeNascimento() == null) throw new Exception("Data de nascimento é obrigatória");
        
        try {
            String sql =  "insert into PessoaFisica(idCliente, cpf, dataDeNascimento) values(?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServico.getIdCliente());
            preparedStatement.setString(2, objServico.getCpf());
            preparedStatement.setDate(3, objServico.getDataDeNascimento());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<PessoaFisica> obterPessoasFisicas() throws Exception {
        ArrayList<PessoaFisica> listaPessoaFisica = new ArrayList<>();
        String sql = "select * from PessoaFisica order by idCliente";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                PessoaFisica objPessoaFisisca = new PessoaFisica();
                objPessoaFisisca.setIdCliente(rs.getInt("idCliente"));
                objPessoaFisisca.setCpf(rs.getString("cpf"));
                objPessoaFisisca.setDataDeNascimento(rs.getDate("dataDeNascimento"));
                listaPessoaFisica.add(objPessoaFisisca);
            }
            return listaPessoaFisica;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public PessoaFisica buscaPorIdCliente(int idCliente) throws Exception {
        PessoaFisica pessoaFisica = null;
        String sql = "SELECT * FROM PessoaFisica WHERE idCliente = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                pessoaFisica = new PessoaFisica();
                pessoaFisica.setIdCliente(rs.getInt("idCliente"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getDate("dataDeNascimento"));
            }
            return pessoaFisica;
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Pessoa Física por ID: " + e.getMessage());
        }
    }

    @Override
    public PessoaFisica buscaPorCpf(String cpf) throws Exception {
        PessoaFisica pessoaFisica = null;
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("O CPF é obrigatório.");
        }

        String sql = "SELECT * FROM PessoaFisica WHERE cpf = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                pessoaFisica = new PessoaFisica();
                pessoaFisica.setIdCliente(rs.getInt("idCliente"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getDate("dataDeNascimento"));
            }
            return pessoaFisica;
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Pessoa Física por CPF: " + e.getMessage());
        }
    }

    @Override
    public void editar(PessoaFisica objServico) throws Exception {
        if (objServico.getIdCliente() <= 0 || contarDigitos(objServico.getIdCliente ()) < 1) throw new Exception("Id do cliente é obrigatório");
        if (objServico.getCpf() == null || !validarCPF(objServico.getCpf())) throw new Exception("CPF é obrigatório");
        if (objServico.getDataDeNascimento() == null) throw new Exception("Data de nascimento é obrigatória");
        
        try {
            String sql = "UPDATE PessoaFisica SET cpf = ?, dataDeNascimento = ? WHERE idCliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getCpf());
            preparedStatement.setDate(2, objServico.getDataDeNascimento());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma pessoa física encontrada com o id fornecido: " + objServico.getIdCliente());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar a pessoa física: " + erro.getMessage());
        }
    }
}
