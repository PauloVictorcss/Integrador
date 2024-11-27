package models.interfaces;

import java.util.ArrayList;
import models.Marca;

public interface IMarcaCRUD {
  public void incluir(Marca objeto) throws Exception;
  public ArrayList<Marca> obterListaDeMarcas() throws Exception;
  public void editar(Marca objeto) throws Exception;
}
