package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel Fernandes
 */
public class ConsultarOsVisao extends FormPadrao {

    JTable tabelaOrdens;
    DefaultTableModel modeloTabela;
    JTextField campoBuscaCliente;
    JComboBox<String> filtroStatus;
    JLabel jlBuscaCliente, jlFiltroStatus;

    public ConsultarOsVisao() {
        setTitle("LISTA DE ORDEM DE SERVIÇO");

        jlBuscaCliente = new JLabel("Buscar Cliente:");
        jlBuscaCliente.setBounds(10, 10, 100, 25);
        jpCamposDados.add(jlBuscaCliente);

        campoBuscaCliente = new JTextField();
        campoBuscaCliente.setBounds(110, 10, 300, 25);
        jpCamposDados.add(campoBuscaCliente);

        jlFiltroStatus = new JLabel("Status:");
        jlFiltroStatus.setBounds(430, 10, 300, 25);
        jpCamposDados.add(jlFiltroStatus);

        filtroStatus = new JComboBox<>(new String[]{"Todos", "Orçamento", "Aprovação", "Execução", "Finalizada", "Paga"});
        filtroStatus.setBounds(480, 10, 150, 25);
        jpCamposDados.add(filtroStatus);

        //Configuração da tabela no painel
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Cliente", "Veículo", "Modelo", "Status", "Data Início", "Data Fim", "Valor Total"}, 0);
        tabelaOrdens = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaOrdens);

        // Configuração dos botões herdados
        configurarEventos();
    }

    private void configurarEventos() {
        // Botão Consultar
        jbConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarOrdens();
            }
        });

        // Botão Excluir
        jbExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirOrdem();
            }
        });

        // Botão Alterar
        jbAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarOrdem();
            }
        });
    }

    private void buscarOrdens() {
        String cliente = campoBuscaCliente.getText();
        String status = (String) filtroStatus.getSelectedItem();

        modeloTabela.setRowCount(0); // Limpar a tabela antes de buscar

        // Simulação de dados (integração com banco de dados será implementada posteriormente)
        if (cliente.isEmpty() || "João".contains(cliente)) {
            if (status.equals("Todos") || status.equals("Orçamento")) {
                modeloTabela.addRow(new Object[]{1, "João da Silva", "Carro A", "Modelo X", "Orçamento", "2024-11-20", "2024-11-21", "150.00"});
            }
        }
        if (cliente.isEmpty() || "Maria".contains(cliente)) {
            if (status.equals("Todos") || status.equals("Execução")) {
                modeloTabela.addRow(new Object[]{2, "Maria Oliveira", "Carro B", "Modelo Y", "Execução", "2024-11-22", "2024-11-25", "300.00"});
            }
        }
    }

    private void excluirOrdem() {
        int selectedRow = tabelaOrdens.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) modeloTabela.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir a OS de ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Ordem de Serviço excluída com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma OS para excluir.");
        }
    }

    private void alterarOrdem() {
        int selectedRow = tabelaOrdens.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) modeloTabela.getValueAt(selectedRow, 0);
            JOptionPane.showMessageDialog(this, "Abrir tela de edição para OS de ID " + id + ".");
            // Aqui você pode abrir a tela de edição ou preencher os campos com os dados da linha selecionada
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma OS para alterar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarOsVisao tela = new ConsultarOsVisao();
            tela.setVisible(true);
        });
    }

}
