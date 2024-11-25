package persistency;

import Tools.ConexaoBD;
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
    try {
      String sql =  "insert into servrealiz(nomeDaOficina, email, ddi, ddd, numeroTelefone, logradouro, numeroEndereco, complemento, setor, cidade)"
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
        String sql = "select * from servrealiz order by idservrealiz";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Oficina objOficinas = new Oficina();
                objOficinas.setNomeDaOficina(rs.getString("nomeDaOficina"));
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
  
  public Oficina editar(Oficina objeto) throws Exception {
      return new Oficina();
  }
}
