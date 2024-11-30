package models.interfaces;

import java.util.ArrayList;
import models.PessoaJuridica;

public interface IPessoaJuridicaCRUD {
    public void incluir(PessoaJuridica objeto) throws Exception;
    public ArrayList<PessoaJuridica> obterPessoasJuridica() throws Exception;
    public PessoaJuridica buscaPorIdCliente(int idCliente) throws Exception;
    public PessoaJuridica buscaPorCnpj(String cnpj) throws Exception;
    public void editar(PessoaJuridica objeto) throws Exception;
}
