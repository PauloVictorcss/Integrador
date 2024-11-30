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
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Fernandes
 */
public class CadastroMarcaVisao extends FormPadrao {

    JLabel jlIdMarca, jlNomeMarca, jlDescricaoMarca;
    JTextField jtfIdMarca, jtfNomeMarca, jtfDescricaoMarca;
    JSeparator separator;

    JTable tabelaMarca;
    DefaultTableModel modelMarcas;

    JPanel jpTabelas;

    public CadastroMarcaVisao() {
        setTitle("CADASTRO MARCA E MODELO");

        //Criação Marca
        jlIdMarca = new JLabel("ID:");
        jlIdMarca.setBounds(9, 5, 50, 25);
        jpCamposDados.add(jlIdMarca);

        jtfIdMarca = new JTextField();
        jtfIdMarca.setBounds(30, 5, 50, 25);
        jpCamposDados.add(jtfIdMarca);
        jtfIdMarca.setEditable(false);

        jlNomeMarca = new JLabel("Nome da Marca:");
        jlNomeMarca.setBounds(90, 5, 100, 25);
        jpCamposDados.add(jlNomeMarca);

        jtfNomeMarca = new JTextField();
        jtfNomeMarca.setBounds(190, 5, 600, 25);
        jpCamposDados.add(jtfNomeMarca);

        jlDescricaoMarca = new JLabel("Descrição:");
        jlDescricaoMarca.setBounds(9, 40, 100, 25);
        jpCamposDados.add(jlDescricaoMarca);

        jtfDescricaoMarca = new JTextField();
        jtfDescricaoMarca.setBounds(9, 60, 780, 50);
        jpCamposDados.add(jtfDescricaoMarca);

        //Separador
        separator = new JSeparator();
        separator.setBounds(0, 125, 800, 10);
        jpCamposDados.add(separator);

        

        //Configuração das Tabelas no jspListagem do FormPadrao
        modelMarcas = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição"}, 0);
        tabelaMarca = new JTable(modelMarcas);

        //Painel para Juntas as tabelas e Listar no jspListagem do FormPadrao
        jpTabelas = new JPanel();
        jpTabelas.setLayout(new GridLayout(2, 1, 10, 10));
        jpTabelas.add(new JScrollPane(tabelaMarca));

        jspListagem.setViewportView(jpTabelas);

    }

    //Ação do clicar no botao salvar.
    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {

        String nomeMarca = jtfNomeMarca.getText().trim();
        String descricaoMarca = jtfDescricaoMarca.getText().trim();
        //Adicionar Marca a tabela
        if (nomeMarca.isEmpty() || descricaoMarca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos de Marca.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
}
