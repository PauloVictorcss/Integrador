package models.interfaces;

import java.util.ArrayList;
import models.PessoaJuridica;

public interface IPessoaJuridicaCRUD {
    public void incluir(PessoaJuridica objeto) throws Exception;
    public ArrayList<PessoaJuridica> obterPessoasJuridica() throws Exception;
    public void editar(PessoaJuridica objeto) throws Exception;
}
