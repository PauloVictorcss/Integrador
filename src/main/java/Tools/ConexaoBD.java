/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paulo Elem
 */


public class ConexaoBD {
    
  private static Connection conexao = null;  
  private ConexaoBD(){}
  public static Connection getConexao() throws Exception{
    try{
      if(conexao == null){
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost/Oficina_Car";
        String user = "postgres";
        String password = "aluno";
                  
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, user, password);
      }       
    }
    catch(ClassNotFoundException erro){
      //Erro de não encontrar o drive do banco no projeto
      throw new Exception("Drive: "+erro.getMessage());
    }
    catch(SQLException erro){
      //Erro no banco de dados: usuario, senha ou banco de dados 
      throw new Exception("Ocorreu erro no banco: " + erro.getMessage());
    } 
    return conexao;
        
  }
    
}
