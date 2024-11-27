package models.interfaces;

import java.util.ArrayList;
import models.Funcionario;

public interface IFuncionarioCRUD {
    public void incluir(Funcionario objeto) throws Exception;
    public ArrayList<Funcionario> obterListaDeFuncionarios() throws Exception;
    public void editar(Funcionario objeto) throws Exception;
}
