package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel Fernandes
 */
public class PecaSubstituirVisao extends FormPadrao {
    
    JLabel jlId, jlIdOS, jlIdPeca, jlDescricao, jlQuantidade, jlValorUnitario, jlValorTotal;
    JTextField jtfId, jtfIdOS, jtfIdPeca, jtfDescricao, jtfQuantidade, jtfValorUnitario, jtfValorTotal;
    
    JTable tabelaPecasSubstituir;
    DefaultTableModel modeloTabela;

    public PecaSubstituirVisao() {
        setTitle("GERENCIAMENTO DE PEÇAS A SUBISTITUIR");
        
        jlId = new JLabel("ID:");
        jlId.setBounds(10, 10, 50, 25);
        jpCamposDados.add(jlId);

        jtfId = new JTextField();
        jtfId.setBounds(50, 10, 50, 25);
        jpCamposDados.add(jtfId);
        jtfId.setEditable(false);
        
        jlIdOS = new JLabel("ID OS:");
        jlIdOS.setBounds(120, 10, 50, 25);
        jpCamposDados.add(jlIdOS);

        jtfIdOS = new JTextField();
        jtfIdOS.setBounds(170, 10, 100, 25);
        jpCamposDados.add(jtfIdOS);
        
        jlIdPeca = new JLabel("ID Peça:");
        jlIdPeca.setBounds(290, 10, 60, 25);
        jpCamposDados.add(jlIdPeca);

        jtfIdPeca = new JTextField();
        jtfIdPeca.setBounds(350, 10, 100, 25);
        jpCamposDados.add(jtfIdPeca);
        
        jlDescricao = new JLabel("Descrição:");
        jlDescricao.setBounds(10, 50, 80, 25);
        jpCamposDados.add(jlDescricao);

        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(90, 50, 360, 25);
        jpCamposDados.add(jtfDescricao);
        
        jlQuantidade = new JLabel("Quantidade:");
        jlQuantidade.setBounds(10, 90, 80, 25);
        jpCamposDados.add(jlQuantidade);

        jtfQuantidade = new JTextField();
        jtfQuantidade.setBounds(90, 90, 100, 25);
        jpCamposDados.add(jtfQuantidade);
        
        jlValorUnitario = new JLabel("Valor Unitário:");
        jlValorUnitario.setBounds(210, 90, 100, 25);
        jpCamposDados.add(jlValorUnitario);

        jtfValorUnitario = new JTextField();
        jtfValorUnitario.setBounds(310, 90, 100, 25);
        jpCamposDados.add(jtfValorUnitario);
        
        jlValorTotal = new JLabel("Valor Total:");
        jlValorTotal.setBounds(430, 90, 100, 25);
        jpCamposDados.add(jlValorTotal);

        jtfValorTotal = new JTextField();
        jtfValorTotal.setBounds(500, 90, 100, 25);
        jpCamposDados.add(jtfValorTotal);
        jtfValorTotal.setEditable(false);
        
        // Configuração da tabela no jspListagem
        modeloTabela = new DefaultTableModel(new String[]{"ID", "ID OS", "ID Peça", "Descrição", "Quantidade", "Valor Unitário", "Valor Total"}, 0);
        tabelaPecasSubstituir = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaPecasSubstituir);
        
        // Eventos dos botões
        configurarEventos();
    }
    
    private void configurarEventos() {
        jbAdicionar.addActionListener((ActionEvent e) -> {
            salvarPecaSubstituir();
        });

        jbExcluir.addActionListener((ActionEvent e) -> {
            excluirPecaSubstituir();
        });

        jbAlterar.addActionListener((ActionEvent e) -> {
            alterarPecaSubstituir();
        });
    }
    
    private void salvarPecaSubstituir() {
        try {
            int id = modeloTabela.getRowCount() + 1;
            int idOS = Integer.parseInt(jtfIdOS.getText());
            int idPeca = Integer.parseInt(jtfIdPeca.getText());
            String descricao = jtfDescricao.getText();
            int quantidade = Integer.parseInt(jtfQuantidade.getText());
            double valorUnitario = Double.parseDouble(jtfValorUnitario.getText());
            double valorTotal = quantidade * valorUnitario;

            modeloTabela.addRow(new Object[]{id, idOS, idPeca, descricao, quantidade, valorUnitario, valorTotal});
            jtfValorTotal.setText(String.valueOf(valorTotal));

            JOptionPane.showMessageDialog(this, "Peça a Substituir salva com sucesso!");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void excluirPecaSubstituir() {
        int selectedRow = tabelaPecasSubstituir.getSelectedRow();
        if (selectedRow != -1) {
            modeloTabela.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Peça a Substituir excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma peça para excluir.");
        }
    }
    
    private void alterarPecaSubstituir() {
        int selectedRow = tabelaPecasSubstituir.getSelectedRow();
        if (selectedRow != -1) {
            jtfIdOS.setText(modeloTabela.getValueAt(selectedRow, 1).toString());
            jtfIdPeca.setText(modeloTabela.getValueAt(selectedRow, 2).toString());
            jtfDescricao.setText(modeloTabela.getValueAt(selectedRow, 3).toString());
            jtfQuantidade.setText(modeloTabela.getValueAt(selectedRow, 4).toString());
            jtfValorUnitario.setText(modeloTabela.getValueAt(selectedRow, 5).toString());
            jtfValorTotal.setText(modeloTabela.getValueAt(selectedRow, 6).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma peça para alterar.");
        }
    }
    
    private void limparCampos() {
        jtfIdOS.setText("");
        jtfIdPeca.setText("");
        jtfDescricao.setText("");
        jtfQuantidade.setText("");
        jtfValorUnitario.setText("");
        jtfValorTotal.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PecaSubstituirVisao tela = new PecaSubstituirVisao();
            tela.setVisible(true);
        });
    }
    
    
}
