package visao;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Fernandes
 */
public class GerenciarServicosVisao extends FormPadrao {
    
    private JComboBox<String> filtroStatus;
    private DefaultTableModel modeloTabela;
    private JTextField jtfIdServico, jtfCliente, jtfServico, jtfDescricao, jtfDataInicio, jtfDataFim;
    private JLabel jlCliente, jlServico, jlDescricao, jlIdServico, jlDataInicio, jlDataFim;

    public GerenciarServicosVisao() {
        setTitle("GERENCIAR SERVIÇOS");
        
        //COnfiguração principal
        JLabel jlFiltroStatus = new JLabel("Filtrar por Status");
        jlFiltroStatus.setBounds(10, 10, 150, 25);
        jpCamposDados.add(jlFiltroStatus);
        
        filtroStatus = new JComboBox<>(new String[]{"Todos", "Execução", "Aguardando Execução", "Finalizado"});
        filtroStatus.setBounds(130, 10, 200, 25);
        jpCamposDados.add(filtroStatus);
        
        jlIdServico = new JLabel("ID SERVIÇO:");
        jlIdServico.setBounds(350, 10, 100, 25);
        jpCamposDados.add(jlIdServico);
        
        jtfIdServico = new JTextField();
        jtfIdServico.setBounds(420, 10, 80, 25);
        jpCamposDados.add(jtfIdServico);
        
        jlCliente = new JLabel("Cliente:");
        jlCliente.setBounds(10, 50, 80, 25);
        jpCamposDados.add(jlCliente);
        
        jtfCliente = new JTextField();
        jtfCliente.setBounds(50, 50, 720, 25);
        jpCamposDados.add(jtfCliente);
        
        jlServico = new JLabel("Serviços:");
        jlServico.setBounds(10, 80, 100, 25);
        jpCamposDados.add(jlServico);
        
        jtfServico = new JTextField();
        jtfServico.setBounds(60, 80, 710, 50);
        jpCamposDados.add(jtfServico);
        
        jlDescricao = new JLabel("Descrição:");
        jlDescricao.setBounds(10, 140, 100, 25);
        jpCamposDados.add(jlDescricao);
        
        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(80, 140, 690, 25);
        jpCamposDados.add(jtfDescricao);
        
        jlDataInicio = new JLabel("Data de Inicio:");
        jlDataInicio.setBounds(10, 180, 100, 25);
        jpCamposDados.add(jlDataInicio);
        
        jtfDataInicio = new JTextField();
        jtfDataInicio.setBounds(100, 180, 150, 25);
        jpCamposDados.add(jtfDataInicio);
        
        jlDataFim = new JLabel("Data Fim:");
        jlDataFim.setBounds(300, 180, 100, 25);
        jpCamposDados.add(jlDataFim);
        
        jtfDataFim = new JTextField();
        jtfDataFim.setBounds(350, 180, 150, 25);
        jpCamposDados.add(jtfDataFim);
        
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Cliente", "Serviço", "Status", "Data Inicio", "Data Fim"}, 0);
        JTable tabelaServicos = new JTable(modeloTabela);
        jspListagem.setViewportView(tabelaServicos);
    }
    
    
}
