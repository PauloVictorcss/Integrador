package models.interfaces;

import java.util.ArrayList;
import models.Oficina;

public interface IOficinaCRUD {
  public void incluir(Oficina objeto) throws Exception;
  public ArrayList<Oficina> obterListaDeOficinas() throws Exception;
  public Oficina editar(Oficina objeto) throws Exception;
}
