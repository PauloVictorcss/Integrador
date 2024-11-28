package models.interfaces;

import java.util.ArrayList;
import models.PecaSubstituir;

public interface IPecaSubstituirCRUD {
    public void incluir(PecaSubstituir objeto) throws Exception;
    public ArrayList<PecaSubstituir> obterListaDePecaSubstituir() throws Exception;
    public void editar(PecaSubstituir objeto) throws Exception;
}
