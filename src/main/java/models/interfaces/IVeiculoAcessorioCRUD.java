package models.interfaces;

import java.util.ArrayList;
import models.VeiculoAcessorio;

public interface IVeiculoAcessorioCRUD {
  public void incluir(VeiculoAcessorio objeto) throws Exception;
  public ArrayList<VeiculoAcessorio> obterListaDeVeiculoAcessorio() throws Exception;
  public VeiculoAcessorio buscarPorId(int id) throws Exception;
  public ArrayList<VeiculoAcessorio> buscarPorNomeAcessorioNoVeiculo(String nome) throws Exception;
  public ArrayList<VeiculoAcessorio> buscarPorPlacaVeiculo(String placa) throws Exception;
  public void editar(VeiculoAcessorio objeto) throws Exception;    
}
