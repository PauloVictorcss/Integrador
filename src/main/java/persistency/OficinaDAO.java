package persistency;

import Tools.ConexaoBD;
import static Tools.Validacoes.contarDigitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Oficina;
import models.interfaces.IOficinaCRUD;

public class OficinaDAO implements IOficinaCRUD {
    //Conexao com o banco
    private Connection conexao = null;
    public OficinaDAO()throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Oficina objServico) throws Exception {
        if (objServico.getNomeDaOficina() == null || "".equals(objServico.getNomeDaOficina())) throw new Exception("Nome é obrigatório");
        if (objServico.getEmail() == null || "".equals(objServico.getEmail())) throw new Exception("Email é obrigatório");
        if (contarDigitos(objServico.getDdi()) != 2 || objServico.getDdi() == 00) throw new Exception("DDI é obrigatório");
        if (contarDigitos(objServico.getDdd()) != 2 || objServico.getDdd() == 00) throw new Exception("DDD é obrigatório");
        if (contarDigitos(objServico.getNumeroTelefone()) < 8) throw new Exception("Numero do telefone é obrigatório");
        if (contarDigitos(objServico.getNumeroEndereco()) < 1) throw new Exception("Numero do Endereco é obrigatório");
        if (objServico.getLogradouro() == null || "".equals(objServico.getLogradouro())) throw new Exception("Logradouro é obrigatório");
        if (objServico.getComplemento() == null || "".equals(objServico.getComplemento())) throw new Exception("Complemento é obrigatório");
        if (objServico.getSetor() == null || "".equals(objServico.getSetor())) throw new Exception("Setor é obrigatório");
        if (objServico.getCidade() == null || "".equals(objServico.getCidade())) throw new Exception("Cidade é obrigatória");
        
        try {
            String sql =  "insert into Oficina(nomeOficina, email, ddi, ddd, numeroTelefone, logradouro, numeroEndereco, complemento, setor, cidade)"
                    +     "values(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getNomeDaOficina());
            preparedStatement.setString(2, objServico.getEmail());
            preparedStatement.setInt(3, objServico.getDdi());
            preparedStatement.setInt(4,objServico.getDdd());
            preparedStatement.setInt(5, objServico.getNumeroTelefone());
            preparedStatement.setString(6, objServico.getLogradouro());
            preparedStatement.setInt(7, objServico.getNumeroEndereco());
            preparedStatement.setString(8, objServico.getComplemento());
            preparedStatement.setString(9, objServico.getSetor());
            preparedStatement.setString(10, objServico.getCidade());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }   
    }
  
    @Override
    public ArrayList<Oficina> obterListaDeOficinas() throws Exception {
        ArrayList<Oficina> listaDeOficinas = new ArrayList<>();
        String sql = "select * from Oficina order by nomeOficina";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Oficina objOficinas = new Oficina();
                objOficinas.setNomeDaOficina(rs.getString("nomeOficina"));
                objOficinas.setEmail(rs.getString("email"));
                objOficinas.setDdd(rs.getInt("ddi"));
                objOficinas.setDdd(rs.getInt("ddd"));
                objOficinas.setNumeroTelefone(rs.getInt("numeroTelefone"));
                objOficinas.setLogradouro(rs.getString("logradouro"));
                objOficinas.setNumeroEndereco(rs.getInt("numeroEndereco"));
                objOficinas.setComplemento(rs.getString("complemento"));
                objOficinas.setSetor(rs.getString("setor"));
                objOficinas.setCidade(rs.getString("cidade"));
                listaDeOficinas.add(objOficinas);
            }
            return listaDeOficinas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public Oficina buscaPorNome(String nomeOficina) throws Exception {
        if (nomeOficina == null || nomeOficina.trim().isEmpty()) throw new Exception("O nome da oficina é obrigatório.");

        Oficina oficinaEncontrada = null;
        String sql = "SELECT * FROM Oficina WHERE nomeOficina = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nomeOficina);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                oficinaEncontrada = new Oficina();
                oficinaEncontrada.setNomeDaOficina(rs.getString("nomeOficina"));
                oficinaEncontrada.setEmail(rs.getString("email"));
                oficinaEncontrada.setDdi(rs.getInt("ddi"));
                oficinaEncontrada.setDdd(rs.getInt("ddd"));
                oficinaEncontrada.setNumeroTelefone(rs.getInt("numeroTelefone"));
                oficinaEncontrada.setLogradouro(rs.getString("logradouro"));
                oficinaEncontrada.setNumeroEndereco(rs.getInt("numeroEndereco"));
                oficinaEncontrada.setComplemento(rs.getString("complemento"));
                oficinaEncontrada.setSetor(rs.getString("setor"));
                oficinaEncontrada.setCidade(rs.getString("cidade"));
            }

            return oficinaEncontrada;
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar a oficina pelo nome: " + e.getMessage());
        }
    }

    @Override
    public void editar(Oficina objServico) throws Exception {
        if (objServico.getNomeDaOficina() == null || "".equals(objServico.getNomeDaOficina())) throw new Exception("Nome é obrigatório");
        if (objServico.getEmail() == null || "".equals(objServico.getEmail())) throw new Exception("Email é obrigatório");
        if (contarDigitos(objServico.getDdi()) != 2 || objServico.getDdi() == 00) throw new Exception("DDI é obrigatório");
        if (contarDigitos(objServico.getDdd()) != 2 || objServico.getDdd() == 00) throw new Exception("DDD é obrigatório");
        if (contarDigitos(objServico.getNumeroTelefone()) < 8) throw new Exception("Numero do telefone é obrigatório");
        if (contarDigitos(objServico.getNumeroEndereco()) < 1) throw new Exception("Numero do Endereco é obrigatório");
        if (objServico.getLogradouro() == null || "".equals(objServico.getLogradouro())) throw new Exception("Logradouro é obrigatório");
        if (objServico.getComplemento() == null || "".equals(objServico.getComplemento())) throw new Exception("Complemento é obrigatório");
        if (objServico.getSetor() == null || "".equals(objServico.getSetor())) throw new Exception("Setor é obrigatório");
        if (objServico.getCidade() == null || "".equals(objServico.getCidade())) throw new Exception("Cidade é obrigatória");
        
        try {
            String sql = "UPDATE Oficina SET email = ?, logradouro = ?, complemento = ?, setor = ?, cidade = ?, "
                       + "numeroEndereco = ?, ddi = ?, ddd = ?, numeroTelefone = ? WHERE nomeOficina = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objServico.getEmail());
            preparedStatement.setString(2, objServico.getLogradouro());
            preparedStatement.setString(3, objServico.getComplemento());
            preparedStatement.setString(4, objServico.getSetor());
            preparedStatement.setString(5, objServico.getCidade());
            preparedStatement.setInt(6, objServico.getNumeroEndereco());
            preparedStatement.setInt(7, objServico.getDdi());
            preparedStatement.setInt(8, objServico.getDdd());
            preparedStatement.setInt(9, objServico.getNumeroTelefone());
            preparedStatement.setString(10, objServico.getNomeDaOficina());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Nenhuma oficina encontrada com o nome fornecido: " + objServico.getNomeDaOficina());
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao editar oficina: " + erro.getMessage());
        }
    }
}
