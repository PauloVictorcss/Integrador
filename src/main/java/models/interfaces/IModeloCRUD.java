package models.interfaces;

import java.util.ArrayList;
import models.Modelo;

public interface IModeloCRUD {
  public void incluir(Modelo objeto) throws Exception;
  public ArrayList<Modelo> obterListaDeModelos() throws Exception;
  public Modelo buscaPorId(int id) throws Exception;
  public ArrayList<Modelo> buscaPorNome(String nome) throws Exception;
  public void editar(Modelo objeto) throws Exception;
}
