package models.interfaces;

import java.util.ArrayList;
import models.OS;

public interface IOSCRUD {
  public void incluir(OS objeto) throws Exception;
  public ArrayList<OS> obterListaDeOS() throws Exception;
  public void editar(OS objeto) throws Exception;    
}
