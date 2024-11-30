package models.interfaces;

import java.util.ArrayList;
import models.Funcionario;

public interface IFuncionarioCRUD {
    public void incluir(Funcionario objeto) throws Exception;
    public ArrayList<Funcionario> obterListaDeFuncionarios() throws Exception;
    public ArrayList<String> obterListaDeNomesFuncionarios() throws Exception;
    public Funcionario buscaPorId(int id) throws Exception;
    public ArrayList<Funcionario> buscaPorNome(String nome) throws Exception;
    public Funcionario buscaPorCpf(String cpf) throws Exception;
    public void editar(Funcionario objeto) throws Exception;
}
