package models.interfaces;

import java.util.ArrayList;
import models.Servico;

public interface IServicoCRUD {
    public void incluir(Servico objeto) throws Exception;
  public ArrayList<Servico> obterListaDeServicos() throws Exception;
  public void editar(Servico objeto) throws Exception;
}
