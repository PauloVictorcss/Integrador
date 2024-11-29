package models.interfaces;

import java.util.ArrayList;
import models.Veiculo;

public interface IVeiculoCRUD {
  public void incluir(Veiculo objeto) throws Exception;
  public ArrayList<Veiculo> obterListaDeVeiculos() throws Exception;
  public void editar(Veiculo objeto) throws Exception;
}
