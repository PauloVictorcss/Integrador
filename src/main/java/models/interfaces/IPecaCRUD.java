package models.interfaces;

import java.util.ArrayList;
import models.Peca;

public interface IPecaCRUD {
    public void incluir(Peca objeto) throws Exception;
    public ArrayList<Peca> obterListaDePecas() throws Exception;
    public ArrayList<String> obterListaDePecasPorNome() throws Exception;
    public Peca buscaPorId(int id) throws Exception;
    public ArrayList<Peca> buscaPorNome(String nome) throws Exception;
    public void editar(Peca objeto) throws Exception;    
}
