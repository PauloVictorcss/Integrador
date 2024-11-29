package Tools;

import models.Servico;
import persistency.ServicoDAO;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // Testando ServicoDAO
            System.out.println("\n=== Testando ServicoDAO ===");
            ServicoDAO servicoDAO = new ServicoDAO();

            // 1. Incluir um serviço
            Servico novoServico = new Servico("Troca de óleo", "Troca de óleo completa para carros", 150.00);
            servicoDAO.incluir(novoServico);
            System.out.println("Serviço incluído com sucesso!");

            // 2. Listar serviços
            ArrayList<Servico> servicos = servicoDAO.obterListaDeServicos();
            System.out.println("Lista de serviços:");
            for (Servico servico : servicos) {
                System.out.println("Nome: " + servico.getNome());
                System.out.println("Descrição: " + servico.getDescricao());
                System.out.println("Valor Unitário: " + servico.getValorUnitario());
                System.out.println("-----------------------------------");
            }

            // 3. Editar um serviço
            if (!servicos.isEmpty()) {
                Servico servicoEditado = servicos.get(0); // Edita o primeiro serviço da lista
                servicoEditado.setDescricao("Troca de óleo completa com revisão geral");
                servicoEditado.setValorUnitario(200.00);
                servicoDAO.editar(servicoEditado);
                System.out.println("Serviço editado com sucesso!");
            } else {
                System.out.println("Nenhum serviço encontrado para editar.");
            }

            // 4. Listar serviços novamente para verificar as mudanças
            servicos = servicoDAO.obterListaDeServicos();
            System.out.println("Lista de serviços atualizada:");
            for (Servico servico : servicos) {
                System.out.println("Nome: " + servico.getNome());
                System.out.println("Descrição: " + servico.getDescricao());
                System.out.println("Valor Unitário: " + servico.getValorUnitario());
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
