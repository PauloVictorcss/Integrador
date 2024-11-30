package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel Fernandes
 */
public class ServicoExecutadoVisao extends FormPadrao {

    private JTable tabelaServicos;
    private DefaultTableModel modeloTabela;

    // Campos para entrada de dados
    private JTextField jtfIdOS, jtfIdServico, jtfFuncionarioId, jtfDataInicio, jtfDataFim, jtfQuantidade, jtfValorUnitario, jtfValorTotal, jtfDescricao;

    public ServicoExecutadoVisao() {
        setTitle("Serviços Executados");

        // Campos de entrada no painel jpCamposDados
        JLabel lblIdOS = new JLabel("ID OS:");
        lblIdOS.setBounds(10, 10, 80, 25);
        jpCamposDados.add(lblIdOS);

        jtfIdOS = new JTextField();
        jtfIdOS.setBounds(90, 10, 150, 25);
        jpCamposDados.add(jtfIdOS);

        JLabel lblIdServico = new JLabel("ID Serviço:");
        lblIdServico.setBounds(260, 10, 80, 25);
        jpCamposDados.add(lblIdServico);

        jtfIdServico = new JTextField();
        jtfIdServico.setBounds(340, 10, 150, 25);
        jpCamposDados.add(jtfIdServico);

        JLabel lblFuncionarioId = new JLabel("Funcionário ID:");
        lblFuncionarioId.setBounds(10, 50, 100, 25);
        jpCamposDados.add(lblFuncionarioId);

        jtfFuncionarioId = new JTextField();
        jtfFuncionarioId.setBounds(110, 50, 150, 25);
        jpCamposDados.add(jtfFuncionarioId);

        JLabel lblDataInicio = new JLabel("Data Início:");
        lblDataInicio.setBounds(270, 50, 80, 25);
        jpCamposDados.add(lblDataInicio);

        jtfDataInicio = new JTextField();
        jtfDataInicio.setBounds(340, 50, 150, 25);
        jpCamposDados.add(jtfDataInicio);

        JLabel lblDataFim = new JLabel("Data Fim:");
        lblDataFim.setBounds(510, 50, 80, 25);
        jpCamposDados.add(lblDataFim);

        jtfDataFim = new JTextField();
        jtfDataFim.setBounds(580, 50, 150, 25);
        jpCamposDados.add(jtfDataFim);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(10, 90, 80, 25);
        jpCamposDados.add(lblQuantidade);

        jtfQuantidade = new JTextField();
        jtfQuantidade.setBounds(90, 90, 150, 25);
        jpCamposDados.add(jtfQuantidade);

        JLabel lblValorUnitario = new JLabel("Valor Unitário:");
        lblValorUnitario.setBounds(260, 90, 100, 25);
        jpCamposDados.add(lblValorUnitario);

        jtfValorUnitario = new JTextField();
        jtfValorUnitario.setBounds(360, 90, 150, 25);
        jpCamposDados.add(jtfValorUnitario);

        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(520, 90, 80, 25);
        jpCamposDados.add(lblValorTotal);

        jtfValorTotal = new JTextField();
        jtfValorTotal.setBounds(600, 90, 150, 25);
        jpCamposDados.add(jtfValorTotal);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 130, 80, 25);
        jpCamposDados.add(lblDescricao);

        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(90, 130, 660, 25);
        jpCamposDados.add(jtfDescricao);

        // Configuração da tabela no painel jspListagem
        modeloTabela = new DefaultTableModel(new String[]{"ID", "ID OS", "ID Serviço", "Funcionário", "Data Início", "Data Fim", "Quantidade", "Valor Unitário", "Valor Total", "Descrição"}, 0);
        tabelaServicos = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaServicos);

        // Configurar eventos dos botões
        configurarEventos();
    }

    private void configurarEventos() {
        // Evento para salvar um novo serviço executado
        jbAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarServicoExecutado();
            }
        });

        // Evento para excluir um serviço executado
        jbExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirServicoExecutado();
            }
        });

        // Evento para alterar um serviço executado
        jbAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarServicoExecutado();
            }
        });
    }

    private void salvarServicoExecutado() {
        try {
            int idOS = Integer.parseInt(jtfIdOS.getText());
            int idServico = Integer.parseInt(jtfIdServico.getText());
            int funcionarioId = Integer.parseInt(jtfFuncionarioId.getText());
            String dataInicio = jtfDataInicio.getText();
            String dataFim = jtfDataFim.getText();
            int quantidade = Integer.parseInt(jtfQuantidade.getText());
            double valorUnitario = Double.parseDouble(jtfValorUnitario.getText());
            double valorTotal = quantidade * valorUnitario;
            String descricao = jtfDescricao.getText();

            modeloTabela.addRow(new Object[]{modeloTabela.getRowCount() + 1, idOS, idServico, funcionarioId, dataInicio, dataFim, quantidade, valorUnitario, valorTotal, descricao});

            JOptionPane.showMessageDialog(this, "Serviço Executado salvo com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirServicoExecutado() {
        int selectedRow = tabelaServicos.getSelectedRow();
        if (selectedRow != -1) {
            modeloTabela.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Serviço Executado excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um serviço para excluir.");
        }
    }

    private void alterarServicoExecutado() {
        int selectedRow = tabelaServicos.getSelectedRow();
        if (selectedRow != -1) {
            jtfIdOS.setText(modeloTabela.getValueAt(selectedRow, 1).toString());
            jtfIdServico.setText(modeloTabela.getValueAt(selectedRow, 2).toString());
            jtfFuncionarioId.setText(modeloTabela.getValueAt(selectedRow, 3).toString());
            jtfDataInicio.setText(modeloTabela.getValueAt(selectedRow, 4).toString());
            jtfDataFim.setText(modeloTabela.getValueAt(selectedRow, 5).toString());
            jtfQuantidade.setText(modeloTabela.getValueAt(selectedRow, 6).toString());
            jtfValorUnitario.setText(modeloTabela.getValueAt(selectedRow, 7).toString());
            jtfValorTotal.setText(modeloTabela.getValueAt(selectedRow, 8).toString());
            jtfDescricao.setText(modeloTabela.getValueAt(selectedRow, 9).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um serviço para alterar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServicoExecutadoVisao tela = new ServicoExecutadoVisao();
            tela.setVisible(true);
        });
    }
}
