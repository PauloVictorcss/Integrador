package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class RelatorioVisao extends FormPadrao {

    // Componentes para Filtros
    JLabel jlPeriodoInicio, jlPeriodoFim, jlTipoRelatorio;
    JTextField jtfPeriodoInicio, jtfPeriodoFim;
    JComboBox<String> jcbTipoRelatorio;
    JButton jbGerarRelatorio, jbExportar, jbFechar;

    // Tabela
    JTable tabelaRelatorios;
    DefaultTableModel modeloTabela;

    public RelatorioVisao() {
        setTitle("Relatórios");

        // Filtros
        jlPeriodoInicio = new JLabel("Período Início:");
        jlPeriodoInicio.setBounds(10, 10, 100, 25);
        jpCamposDados.add(jlPeriodoInicio);

        jtfPeriodoInicio = new JTextField();
        jtfPeriodoInicio.setBounds(120, 10, 120, 25);
        jpCamposDados.add(jtfPeriodoInicio);

        jlPeriodoFim = new JLabel("Período Fim:");
        jlPeriodoFim.setBounds(250, 10, 100, 25);
        jpCamposDados.add(jlPeriodoFim);

        jtfPeriodoFim = new JTextField();
        jtfPeriodoFim.setBounds(340, 10, 120, 25);
        jpCamposDados.add(jtfPeriodoFim);

        jlTipoRelatorio = new JLabel("Tipo de Relatório:");
        jlTipoRelatorio.setBounds(10, 50, 120, 25);
        jpCamposDados.add(jlTipoRelatorio);

        jcbTipoRelatorio = new JComboBox<>(new String[]{"Selecione", "Relatório de OS", "Relatório de Peças", "Relatório de Serviços"});
        jcbTipoRelatorio.setBounds(120, 50, 200, 25);
        jpCamposDados.add(jcbTipoRelatorio);

        // Botões
        jbGerarRelatorio = new JButton("Gerar Relatório");
        jbGerarRelatorio.setBounds(10, 90, 150, 30);
        jpCamposDados.add(jbGerarRelatorio);

        jbExportar = new JButton("Exportar");
        jbExportar.setBounds(170, 90, 150, 30);
        jpCamposDados.add(jbExportar);

        jbFechar = new JButton("Fechar");
        jbFechar.setBounds(330, 90, 150, 30);
        jpCamposDados.add(jbFechar);

        // Tabela no jspListagem
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Descrição", "Data", "Valor"}, 0);
        tabelaRelatorios = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaRelatorios);

        // Eventos
        configurarEventos();
    }

    private void configurarEventos() {
        // Evento para Gerar Relatório
        jbGerarRelatorio.addActionListener(e -> gerarRelatorio());

        // Evento para Exportar Relatório
        jbExportar.addActionListener(e -> exportarRelatorio());

        // Evento para Fechar a Tela
        jbFechar.addActionListener(e -> dispose());
    }

    private void gerarRelatorio() {
        String periodoInicio = jtfPeriodoInicio.getText();
        String periodoFim = jtfPeriodoFim.getText();
        String tipoRelatorio = (String) jcbTipoRelatorio.getSelectedItem();

        modeloTabela.setRowCount(0); // Limpar a tabela antes de gerar

        // Simulação de dados (lógica real será baseada no banco de dados)
        if (tipoRelatorio.equals("Relatório de OS")) {
            modeloTabela.addRow(new Object[]{1, "Ordem de Serviço 001", "2024-11-01", "500.00"});
            modeloTabela.addRow(new Object[]{2, "Ordem de Serviço 002", "2024-11-10", "300.00"});
        } else if (tipoRelatorio.equals("Relatório de Peças")) {
            modeloTabela.addRow(new Object[]{1, "Peça A", "2024-11-05", "50.00"});
            modeloTabela.addRow(new Object[]{2, "Peça B", "2024-11-15", "100.00"});
        } else if (tipoRelatorio.equals("Relatório de Serviços")) {
            modeloTabela.addRow(new Object[]{1, "Serviço X", "2024-11-07", "200.00"});
            modeloTabela.addRow(new Object[]{2, "Serviço Y", "2024-11-12", "150.00"});
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um tipo de relatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void exportarRelatorio() {
        JOptionPane.showMessageDialog(this, "Exportação de relatórios ainda não implementada.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        // Implementação futura para exportar para PDF/Excel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RelatorioVisao tela = new RelatorioVisao();
            tela.setVisible(true);
        });
    }
}
