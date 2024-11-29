package models.interfaces;

import java.util.ArrayList;
import models.PessoaFisica;

public interface IPessoaFisicaCRUD {
    public void incluir(PessoaFisica objeto) throws Exception;
    public ArrayList<PessoaFisica> obterPessoasFisicas() throws Exception;
    public void editar(PessoaFisica objeto) throws Exception;
}
