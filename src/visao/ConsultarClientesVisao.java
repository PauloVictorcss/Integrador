package visao;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel Fernandes
 */
public class ConsultarClientesVisao extends JFrame {

    JTable tabelaClientes;
    DefaultTableModel modeloTabela;
    JTextField campoBusca;
    JButton btnBuscar, btnSelecionar, btnFechar;

    public ConsultarClientesVisao() {
        setTitle("CONSULTA DE CLIENTES");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Layout Principal da Tela de Consulta
        setLayout(null);

        //Campo de Busca de Clientes
        JLabel jlBusca = new JLabel("Buscar Cliente:");
        add(jlBusca);

        campoBusca = new JTextField();
        campoBusca.setBounds(110, 10, 500, 25);
        add(campoBusca);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(620, 10, 100, 25);
        add(btnBuscar);

        //Tabela de Clientes
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome/Razao Social", "Tipo", "Contato"}, 0);
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaClientes);
        scrollTabela.setBounds(10, 50, 760, 400);
        add(scrollTabela);

        //Botoes
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setBounds(520, 470, 120, 30);
        add(btnSelecionar);

        btnFechar = new JButton("Fechar");
        btnFechar.setBounds(650, 470, 120, 30);
        add(btnFechar);

        configurarEventos();

    }

    private void configurarEventos() {
        // Evento de buscar o cliente
        btnBuscar.addActionListener((ActionEvent e) -> {
            buscarClientes(campoBusca.getText());
        });

        //Evento para selecionar o cliente
        btnSelecionar.addActionListener((ActionEvent e) -> {
            int selectedRow = tabelaClientes.getSelectedRow();
            if (selectedRow != -1) {
                String clienteSelecionado = modeloTabela.getValueAt(selectedRow, 1).toString();
                JOptionPane.showMessageDialog(ConsultarClientesVisao.this,
                        "Cliente Selecionado: " + clienteSelecionado);
            } else {
                JOptionPane.showMessageDialog(ConsultarClientesVisao.this,
                        "Selecione um cliente na Tabela.");
            }
        });

        //Evento pra fechar a Tela
        btnFechar.addActionListener((ActionEvent e) -> {
            dispose();
        });
    }

    private void buscarClientes(String termo) {
        //simular busca de clientes ( a logica real deve ser integrada ao banco de dados ou repositorio )
        modeloTabela.setRowCount(0);

        //Exemplos com Dados Fictiços
        if (termo.isEmpty() || "Joao".contains(termo)) {
            modeloTabela.addRow(new Object[]{1, "João da Silva", "Pessoa Fisica", "joao@gmail.com"});
        }
        if (termo.isEmpty() || "Empresa X".contains(termo)) {
            modeloTabela.addRow(new Object[]{2, "Empresa X Ltda", "Pessoa Juridica", "contato@empresax.com"});
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarClientesVisao telaConsulta = new ConsultarClientesVisao();
            telaConsulta.setVisible(true);
        });
    }
}
