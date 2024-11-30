/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visao;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Fernandes
 */
public class CadastroModeloVisao extends FormPadrao {

    JLabel jlIdModelo, jlNomeModelo, jlDescricaoModelo;
    JTextField jtfIdModelo, jtfNomeModelo, jtfDescricaoModelo;

    JTable tabelaModelo;
    DefaultTableModel modelModelos;

    JPanel jpTabelas;

    public CadastroModeloVisao() {
        setTitle("CADASTRO DE MODELO");

        //Criacao Modelo
        jlIdModelo = new JLabel("ID:");
        jlIdModelo.setBounds(9, 5, 50, 25);
        jpCamposDados.add(jlIdModelo);

        jtfIdModelo = new JTextField();
        jtfIdModelo.setBounds(30, 5, 50, 25);
        jpCamposDados.add(jtfIdModelo);
        jtfIdModelo.setEditable(false);

        jlNomeModelo = new JLabel("Nome do Modelo:");
        jlNomeModelo.setBounds(90, 5, 100, 25);
        jpCamposDados.add(jlNomeModelo);

        jtfNomeModelo = new JTextField();
        jtfNomeModelo.setBounds(190, 5, 600, 25);
        jpCamposDados.add(jtfNomeModelo);

        jlDescricaoModelo = new JLabel("Descrição:");
        jlDescricaoModelo.setBounds(9, 40, 100, 25);
        jpCamposDados.add(jlDescricaoModelo);

        jtfDescricaoModelo = new JTextField();
        jtfDescricaoModelo.setBounds(9, 60, 780, 50);
        jpCamposDados.add(jtfDescricaoModelo);

        modelModelos = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição"}, 0);
        tabelaModelo = new JTable(modelModelos);

        jpTabelas = new JPanel();
        jpTabelas.setLayout(new GridLayout(2, 1, 10, 10));
        jpTabelas.add(new JScrollPane(tabelaModelo));

        jspListagem.setViewportView(jpTabelas);
    }

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {

        String nomeModelo = jtfNomeModelo.getText().trim();
        String descricaoModelo = jtfDescricaoModelo.getText().trim();
        //Adicionar Marca a tabela
        if (nomeModelo.isEmpty() || descricaoModelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos de Marca.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }
}

