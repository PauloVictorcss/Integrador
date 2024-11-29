package models.interfaces;

import java.util.ArrayList;
import models.Cliente;

public interface IClienteCRUD {
    public void incluir(Cliente cliente) throws Exception;
    public ArrayList<Cliente> obterListaDeClientes() throws Exception;
    public void alterar(Cliente cliente) throws Exception; 
}
