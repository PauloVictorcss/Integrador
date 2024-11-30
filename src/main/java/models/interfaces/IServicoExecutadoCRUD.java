package models.interfaces;

import java.util.ArrayList;
import models.ServicoExecutado;

public interface IServicoExecutadoCRUD {
    public void incluir(ServicoExecutado objeto) throws Exception;
    public ArrayList<ServicoExecutado> obterListaDeServicosExecutados() throws Exception;
    public ServicoExecutado buscarPorId(int id) throws Exception;
    public ArrayList<ServicoExecutado> buscarPorIdServico(int idServico) throws Exception;
    public ArrayList<ServicoExecutado> buscarPorIdOS(int idOS) throws Exception;
    public void editar(ServicoExecutado objeto) throws Exception;
}
