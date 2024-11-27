package models.interfaces;

import java.util.ArrayList;
import models.Peca;

public interface IPecaCRUD {
    public void incluir(Peca objeto) throws Exception;
    public ArrayList<Peca> obterListaDePecas() throws Exception;
    public void editar(Peca objeto) throws Exception;    
}
