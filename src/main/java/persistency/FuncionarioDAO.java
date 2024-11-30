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
import models.Funcionario;
import models.interfaces.IFuncionarioCRUD;

public class FuncionarioDAO implements IFuncionarioCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public FuncionarioDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Funcionario objServico) throws Exception {
        if (objServico.getCpf() == null || !validarCPF(objServico.getCpf())) throw new Exception("CPF é obrigatório");
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDataEntrada() == null) throw new Exception("Data de Entrada é obrigatória");
        if (contarDigitos(objServico.getTelefone()) < 8) throw new Exception("Telefone é obrigatório.");
        if (objServico.getEmail() == null || "".equals(objServico.getEmail())) throw new Exception("Email é obrigatório.");
        
        try {
            String sql =  "insert into Funcionario(cpf, nome, dataEntrada, dataSaida, telefone, email)"
                    +     "values(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getCpf());
            preparedStatement.setString(2, objServico.getNome());
            preparedStatement.setDate(3, objServico.getDataEntrada());
            preparedStatement.setDate(4,objServico.getDataSaida());
            preparedStatement.setInt(5, objServico.getTelefone());
            preparedStatement.setString(6, objServico.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<Funcionario> obterListaDeFuncionarios() throws Exception {
        ArrayList<Funcionario> listaDeFuncionarios = new ArrayList<>();
        String sql = "select * from Funcionario order by id";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Funcionario objFuncionarios = new Funcionario();
                objFuncionarios.setId(rs.getInt("id"));
                objFuncionarios.setCpf(rs.getString("cpf"));
                objFuncionarios.setNome(rs.getString("nome"));
                objFuncionarios.setDataEntrada(rs.getDate("dataEntrada"));
                objFuncionarios.setDataSaida(rs.getDate("dataSaida"));
                objFuncionarios.setTelefone(rs.getInt("telefone"));
                objFuncionarios.setEmail(rs.getString("email"));
                listaDeFuncionarios.add(objFuncionarios);
            }
            return listaDeFuncionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public ArrayList<String> obterListaDeNomesFuncionarios() throws Exception {
        ArrayList<String> listaDeNomes = new ArrayList<>();
        String sql = "SELECT nome FROM Funcionario ORDER BY nome";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String nome = rs.getString("nome");
                listaDeNomes.add(nome);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter lista de funcionários: " + e.getMessage());
        }

        return listaDeNomes;
    }

    @Override
    public Funcionario buscaPorId(int id) throws Exception {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM Funcionario WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataEntrada(rs.getDate("dataEntrada"));
                funcionario.setDataSaida(rs.getDate("dataSaida"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Funcionario por ID: " + e.getMessage());
        }

        return funcionario;
    }
    
    @Override
    public ArrayList<Funcionario> buscaPorNome(String nome) throws Exception {
        ArrayList<Funcionario> listaDeFuncionarios = new ArrayList<>();
        if (nome == null || nome.isEmpty()) throw new Exception("Nome é obrigatório");

        String sql = "SELECT * FROM Funcionario WHERE nome LIKE ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataEntrada(rs.getDate("dataEntrada"));
                funcionario.setDataSaida(rs.getDate("dataSaida"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setEmail(rs.getString("email"));
                listaDeFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Funcionário por nome: " + e.getMessage());
        }

        return listaDeFuncionarios;
    }
    
    @Override
    public Funcionario buscaPorCpf(String cpf) throws Exception {
        if (cpf == null || "".equals(cpf)) throw new Exception("CPF é obrigatório");

        Funcionario funcionario = null;
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataEntrada(rs.getDate("dataEntrada"));
                funcionario.setDataSaida(rs.getDate("dataSaida"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar Funcionario por CPF: " + e.getMessage());
        }

        return funcionario;
    }
  
    @Override
    public void editar(Funcionario objServico) throws Exception {
        if (objServico.getCpf() == null || "".equals(objServico.getCpf())) throw new Exception("CPF é obrigatório");
        if (objServico.getNome() == null || "".equals(objServico.getNome())) throw new Exception("Nome é obrigatório");
        if (objServico.getDataEntrada() == null) throw new Exception("Data de Entrada é obrigatória");
        // if (objServico.getTelefone() < 8) throw new Exception("Telefone é obrigatório.");
        if (objServico.getEmail() == null || "".equals(objServico.getEmail())) throw new Exception("Email é obrigatório.");
        
        try {
            String sql = "UPDATE Funcionario SET cpf = ?, nome = ?, dataEntrada = ?, dataSaida = ?, telefone = ?, email = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getCpf());
            preparedStatement.setString(2, objServico.getNome());
            preparedStatement.setDate(3, objServico.getDataEntrada());
            preparedStatement.setDate(4, objServico.getDataSaida());
            preparedStatement.setInt(5, objServico.getTelefone());
            preparedStatement.setString(6, objServico.getEmail());
            preparedStatement.setInt(7, objServico.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhum funcionario encontrado com o id fornecido: " + objServico.getId());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar o funcionario: " + erro.getMessage());
        }
    }
}
