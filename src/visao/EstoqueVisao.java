package visao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel Fernandes
 */
public class EstoqueVisao extends FormPadrao {

    JLabel jlId, jlNomeDaPeca, jlFabricante, jlQuantidade, jlValorCusto, jlValorVenda, jlVolumeTamanho;
    JTextField jtfId, jtfNomeDaPeca, jtfFabricante, jtfQuantidade, jtfValorCusto, jtfValorVenda, jtfVolumeTamanho;

    JTable tabelaPecas;
    DefaultTableModel modeloTabela;

    public EstoqueVisao() {
        setTitle("Gestao de Estoque");

        jlId = new JLabel("ID:");
        jlId.setBounds(9, 5, 50, 25);
        jpCamposDados.add(jlId);

        jtfId = new JTextField();
        jtfId.setBounds(50, 5, 50, 25);
        jpCamposDados.add(jtfId);
        jtfId.setEditable(false);

        jlNomeDaPeca = new JLabel("Nome da Peça:");
        jlNomeDaPeca.setBounds(125, 5, 100, 25);
        jpCamposDados.add(jlNomeDaPeca);

        jtfNomeDaPeca = new JTextField();
        jtfNomeDaPeca.setBounds(210, 5, 550, 25);
        jpCamposDados.add(jtfNomeDaPeca);

        jlFabricante = new JLabel("Fabricante:");
        jlFabricante.setBounds(9, 45, 150, 25);
        jpCamposDados.add(jlFabricante);

        jtfFabricante = new JTextField();
        jtfFabricante.setBounds(9, 65, 250, 25);
        jpCamposDados.add(jtfFabricante);

        jlQuantidade = new JLabel("Quantidade em Estoque:");
        jlQuantidade.setBounds(280, 45, 150, 25);
        jpCamposDados.add(jlQuantidade);

        jtfQuantidade = new JTextField();
        jtfQuantidade.setBounds(280, 65, 250, 25);
        jpCamposDados.add(jtfQuantidade);

        jlValorCusto = new JLabel("Valor de Custo:");
        jlValorCusto.setBounds(9, 90, 150, 25);
        jpCamposDados.add(jlValorCusto);

        jtfValorCusto = new JTextField();
        jtfValorCusto.setBounds(9, 110, 250, 25);
        jpCamposDados.add(jtfValorCusto);

        jlValorVenda = new JLabel("Valor de Venda:");
        jlValorVenda.setBounds(280, 90, 250, 25);
        jpCamposDados.add(jlValorVenda);

        jtfValorVenda = new JTextField();
        jtfValorVenda.setBounds(280, 110, 250, 25);
        jpCamposDados.add(jtfValorVenda);

        jlVolumeTamanho = new JLabel("Volume / Tamanho:");
        jlVolumeTamanho.setBounds(550, 90, 150, 25);
        jpCamposDados.add(jlVolumeTamanho);

        jtfVolumeTamanho = new JTextField();
        jtfVolumeTamanho.setBounds(550, 110, 200, 25);
        jpCamposDados.add(jtfVolumeTamanho);

        //Configuração da Tabela no jspListagem do FormPadrao
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Peça", "Fabricante", "Quantidade", "Custo", "Venda", "Vol / Tam"}, 0);
        tabelaPecas = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaPecas);

        //Evento do Botao salvar
        configurarEventos();

    }

    private void configurarEventos() {
        jbAdicionar.addActionListener((ActionEvent e) -> {
            salvarPeca();
        });

        jbConsultar.addActionListener((ActionEvent e) -> {
            consultarPecas();
        });
    }

    private void salvarPeca() {
        try {
            int id = modeloTabela.getRowCount() + 1;
            String nome = jtfNomeDaPeca.getText();
            String fabricante = jtfFabricante.getText();
            int quantidade = Integer.parseInt(jtfQuantidade.getText());
            double valorCusto = Double.parseDouble(jtfValorCusto.getText());
            double valorVenda = Double.parseDouble(jtfValorVenda.getText());
            String volumeTamanho = jtfVolumeTamanho.getText();

            modeloTabela.addRow(new Object[]{id, nome, fabricante, quantidade, valorCusto, valorVenda, volumeTamanho});

            JOptionPane.showMessageDialog(this, "Peça salva com sucesso!");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarPecas() {
        JOptionPane.showMessageDialog(this, "Função de consulta ativada.");
        //imprementar a integração com o banco de dados
    }

    private void limparCampos() {
        jtfNomeDaPeca.setText("");
        jtfFabricante.setText("");
        jtfQuantidade.setText("");
        jtfValorCusto.setText("");
        jtfValorVenda.setText("");
        jtfVolumeTamanho.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstoqueVisao tela = new EstoqueVisao();
            tela.setVisible(true);
        });
    }
    

}
