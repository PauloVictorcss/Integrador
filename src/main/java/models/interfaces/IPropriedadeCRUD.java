package models.interfaces;

import java.util.ArrayList;
import models.Propriedade;

public interface IPropriedadeCRUD {
  public void incluir(Propriedade objeto) throws Exception;
  public ArrayList<Propriedade> obterListaDePropriedades() throws Exception;
  public void editar(Propriedade objeto) throws Exception;
}
