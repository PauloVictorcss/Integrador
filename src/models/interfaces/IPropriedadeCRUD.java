package models.interfaces;

import java.util.ArrayList;
import models.Propriedade;

public interface IPropriedadeCRUD {
  public void incluir(Propriedade objeto) throws Exception;
  public ArrayList<Propriedade> obterListaDePropriedades() throws Exception;
  public Propriedade buscarPorId(int id) throws Exception;
  public ArrayList<Propriedade> buscarPorIdCliente(int idCliente) throws Exception;
  public ArrayList<Propriedade> buscarPorPlacaVeiculo(String placaVeiculo) throws Exception;
  public void editar(Propriedade objeto) throws Exception;
}
