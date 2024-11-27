package models.interfaces;

import java.util.ArrayList;
import models.Acessorio;

public interface IAcessorioCRUD {
    public void incluir(Acessorio objeto) throws Exception;
    public ArrayList<Acessorio> obterListaDeAcessorios() throws Exception;
    public void editar(Acessorio objeto) throws Exception;
}
