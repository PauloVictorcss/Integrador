package visao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Fernandes
 */
public class AcessoriosVisao extends FormPadrao {
    
    JLabel jlNome, jlDescricao, jlValor, jlQuantidade;
    JTextField jtfNome, jtfDescricao, jtfValor, jtfQuantidade;
    
    JTable tabelaListagem;
    DefaultTableModel modelListagem;
    
    public AcessoriosVisao() {
        setTitle("CADASTRO DE ACESSORIOS");
        
        jlNome = new JLabel("Nome:");
        jlNome.setBounds(9, 5, 50, 25);
        jpCamposDados.add(jlNome);
        
        jtfNome = new JTextField();
        jtfNome.setBounds(9, 30, 650, 25);
        jpCamposDados.add(jtfNome);
        
        jlDescricao = new JLabel("Descrição:");
        jlDescricao.setBounds(9, 60, 150, 25);
        jpCamposDados.add(jlDescricao);
        
        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(9, 85, 650, 100);
        jpCamposDados.add(jtfDescricao);
        
        jlValor = new JLabel("Valor:");
        jlValor.setBounds(9, 180, 50, 25);
        jpCamposDados.add(jlValor);
        
        jtfValor = new JTextField();
        jtfValor.setBounds(9, 200, 150, 25);
        jpCamposDados.add(jtfValor);
        
        jlQuantidade = new JLabel("Quantidade:");
        jlQuantidade.setBounds(180, 180, 100, 25);
        jpCamposDados.add(jlQuantidade);
        
        jtfQuantidade = new JTextField();
        jtfQuantidade.setBounds(180, 200, 250, 25);
        jpCamposDados.add(jtfQuantidade);
        
        //Configuração da Tabela no jspListagem no FormPadrao
        modelListagem = new DefaultTableModel(new String[]{"Nome", "Descrição", "Quantidade", "Valor"}, 0);
        tabelaListagem = new JTable(modelListagem);
        jspListagem.setViewportView(tabelaListagem);
        
   }
}
