package models.interfaces;

import java.util.ArrayList;
import models.Cliente;

public interface IClienteCRUD {
    public void incluir(Cliente cliente) throws Exception;
    public ArrayList<Cliente> obterListaDeClientes() throws Exception;
    public ArrayList<String> obterListaDeNomeClientes() throws Exception;
    public Cliente buscarPorId(int id) throws Exception;
    public ArrayList<Cliente> buscarPorNome(String nomeCliente) throws Exception;
    public Cliente buscarPorEmail(String email) throws Exception;
    public void alterar(Cliente cliente) throws Exception; 
}
