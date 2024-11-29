package persistency;

import Tools.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;
import models.interfaces.IClienteCRUD;

public class ClienteDAO implements IClienteCRUD{
    
     ArrayList<Cliente> listaCliente;
    
    //Conexao com o banco
    private Connection conexao = null;
    public ClienteDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Cliente cliente) throws Exception {
        try{
           String sql = "insert into Cliente(nomeCliente, email, ddi, ddd, numeroTelefone, logradouro, numeroCasa, complemento, setor, cidade, uf, cep)" 
                   + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
           
           PreparedStatement preparedStatement = conexao.prepareStatement(sql);
           preparedStatement.setString(1, cliente.getNomeCliente());
           preparedStatement.setString(2, cliente.getEmail());
           preparedStatement.setInt(3, cliente.getDdi());
           preparedStatement.setInt(4, cliente.getDdd());
           preparedStatement.setInt(5, cliente.getNumeroTelefone());
           preparedStatement.setString(6, cliente.getLogradouro());
           preparedStatement.setInt(7, cliente.getNumeroCasa());
           preparedStatement.setString(8, cliente.getComplemento());
           preparedStatement.setString(9, cliente.getSetor());
           preparedStatement.setString(10, cliente.getCidade());
           preparedStatement.setString(11, cliente.getUfEstado());
           preparedStatement.setString(12, cliente.getCep());
           preparedStatement.executeUpdate();
        }catch(SQLException erro){
            throw new Exception("SQL Erro: "+ erro.getMessage());
        }catch(Exception erro){
             throw new Exception("Incluir Persistencia: " + erro);
        } 
    }

    @Override
    public ArrayList<Cliente> obterListaDeClientes() throws Exception {
         listaCliente = new ArrayList<>(); 
         
         String sql = "select * from Cliente order by id";
         
         try{
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDdi(rs.getInt("ddi"));
                cliente.setDdd(rs.getInt("ddd"));
                cliente.setNumeroTelefone(rs.getInt("numeroTelefone"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumeroCasa(rs.getInt("numeroCasa"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setSetor(rs.getString("setor"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUfEstado(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                listaCliente.add(cliente);
            }
            return listaCliente;
             
         }catch(SQLException e){
             e.printStackTrace();
         }
         return null;
    }

    @Override
    public void alterar(Cliente cliente) throws Exception {
        try{
          
            String sql = "update Cliente set nomeCliente = ? , email = ?, ddi = ?, ddd = ?, numeroTelefone = ?, logradouro = ?,"
                    + "numeroCasa = ?, complemento = ?, setor = ?, cidade = ?, uf = ?, cep = ? "
                    + "where id = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNomeCliente());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setInt(3, cliente.getDdi());
            preparedStatement.setInt(4, cliente.getDdd());
            preparedStatement.setInt(5, cliente.getNumeroTelefone());
            preparedStatement.setString(6, cliente.getLogradouro());
            preparedStatement.setInt(7, cliente.getNumeroCasa());
            preparedStatement.setString(8, cliente.getComplemento());
            preparedStatement.setString(9, cliente.getSetor());
            preparedStatement.setString(10, cliente.getCidade());
            preparedStatement.setString(11, cliente.getUfEstado());
            preparedStatement.setString(12, cliente.getCep());
            preparedStatement.setInt(13, cliente.getId());
            preparedStatement.executeUpdate();
     
        }catch(SQLException erro){
            throw new Exception("SQL Erro: "+ erro.getMessage());
        }catch(Exception erro){
            throw new Exception("Alterar Veiculos Persistencia: " + erro);
        }
    }  
    
    public List obter(){
        return listaCliente;
    }
}