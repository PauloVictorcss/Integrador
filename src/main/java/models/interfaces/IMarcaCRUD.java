package models.interfaces;

import java.util.ArrayList;
import models.Marca;

public interface IMarcaCRUD {
  public void incluir(Marca objeto) throws Exception;
  public ArrayList<Marca> obterListaDeMarcas() throws Exception;
  public Marca buscaPorId(int id) throws Exception;
  public ArrayList<Marca> buscaPorNome(String nome) throws Exception;
  public void editar(Marca objeto) throws Exception;
}
