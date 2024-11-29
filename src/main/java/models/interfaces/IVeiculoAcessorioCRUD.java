package models.interfaces;

import java.util.ArrayList;
import models.VeiculoAcessorio;

public interface IVeiculoAcessorioCRUD {
  public void incluir(VeiculoAcessorio objeto) throws Exception;
  public ArrayList<VeiculoAcessorio> obterListaDeVeiculoAcessorio() throws Exception;
  public void editar(VeiculoAcessorio objeto) throws Exception;    
}
