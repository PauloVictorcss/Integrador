package models.interfaces;

import java.util.ArrayList;
import models.EnumStatus;
import models.OS;

public interface IOSCRUD {
  public void incluir(OS objeto) throws Exception;
  public ArrayList<OS> obterListaDeOS() throws Exception;
  public OS buscarPorId(int id) throws Exception;
  public ArrayList<OS> buscarPorStatus(EnumStatus status) throws Exception;
  public void editar(OS objeto) throws Exception;    
}
