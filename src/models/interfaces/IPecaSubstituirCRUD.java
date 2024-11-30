package models.interfaces;

import java.util.ArrayList;
import models.PecaSubstituir;

public interface IPecaSubstituirCRUD {
    public void incluir(PecaSubstituir objeto) throws Exception;
    public ArrayList<PecaSubstituir> obterListaDePecaSubstituir() throws Exception;
    public PecaSubstituir buscaPorId(int id) throws Exception;
    public ArrayList<PecaSubstituir> buscarPorIdPeca(int idPeca) throws Exception;
    public ArrayList<PecaSubstituir> buscarPorIdOS(int idOS) throws Exception;
    public void editar(PecaSubstituir objeto) throws Exception;
}
