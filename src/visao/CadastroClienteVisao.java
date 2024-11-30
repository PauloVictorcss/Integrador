package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import javax.swing.*;

/**
 *
 * @author Gabriel Fernandes
 */
public class CadastroClienteVisao extends CadastroCliente {
    

    JLabel jlCPF, jlNome, jlDataNascimento, jlCNPJ, jlRazaoSocial, jlResponsavel, jlNomeFantasia;
    JTextField jtfCPF, jtfNome, jtfDataNascimento, jtfCNPJ, jtfRazaoSocial, jtfResponsavel, jtfNomeFantasia;
    private JRadioButton rbFisica;
    private JRadioButton rbJuridica;
    private ButtonGroup bgTipoPessoa;

    //Metodo Construtor
    public CadastroClienteVisao() {
        setTitle("Cadastro de Clientes");

        //Pessoa Fisica
        jlCPF = new JLabel("CPF:");
        jlCPF.setBounds(400, 50, 50, 25);
        jpDadosBasicos.add(jlCPF);

        jtfCPF = new JTextField();
        jtfCPF.setBounds(400, 72, 150, 25);
        jpDadosBasicos.add(jtfCPF);

        jlNome = new JLabel("Nome:");
        jlNome.setBounds(580, 50, 50, 25);
        jpDadosBasicos.add(jlNome);

        jtfNome = new JTextField();
        jtfNome.setBounds(580, 72, 300, 25);
        jpDadosBasicos.add(jtfNome);

        jlDataNascimento = new JLabel("Data de Nascimento:");
        jlDataNascimento.setBounds(8, 110, 150, 25);
        jpDadosBasicos.add(jlDataNascimento);

        jtfDataNascimento = new JTextField();
        jtfDataNascimento.setBounds(8, 135, 150, 25);
        jpDadosBasicos.add(jtfDataNascimento);
        
        
        //Pessoa Juridica
        jlCNPJ = new JLabel("CNPJ:");
        jlCNPJ.setBounds(400, 50, 50, 25);
        jpDadosBasicos.add(jlCNPJ);
        jlCNPJ.setVisible(false);
        
        jtfCNPJ = new JTextField();
        jtfCNPJ.setBounds(360, 72, 150, 25);
        jpDadosBasicos.add(jtfCNPJ);
        jtfCNPJ.setVisible(false);
        
        jlRazaoSocial = new JLabel("Razão Social:");
        jlRazaoSocial.setBounds(580, 50, 100, 25);
        jpDadosBasicos.add(jlRazaoSocial);
        jlRazaoSocial.setVisible(false);
        
        jtfRazaoSocial = new JTextField();
        jtfRazaoSocial.setBounds(580, 72, 300, 25);
        jpDadosBasicos.add(jtfRazaoSocial);
        jtfRazaoSocial.setVisible(false);
        
        jlNomeFantasia = new JLabel("Nome Fantasia:");
        jlNomeFantasia.setBounds(8, 110, 100, 25);
        jpDadosBasicos.add(jlNomeFantasia);
        jlNomeFantasia.setVisible(false);
        
        jtfNomeFantasia = new JTextField();
        jtfNomeFantasia.setBounds(8, 130, 300, 25);
        jpDadosBasicos.add(jtfNomeFantasia);
        jtfNomeFantasia.setVisible(false);
        
        jlResponsavel = new JLabel("Responsavel:");
        jlResponsavel.setBounds(320, 110, 250, 25);
        jpDadosBasicos.add(jlResponsavel);
        jlResponsavel.setVisible(false);
        
        jtfResponsavel = new JTextField();
        jtfResponsavel.setBounds(320, 130, 250, 25);
        jpDadosBasicos.add(jtfResponsavel);
        jtfResponsavel.setVisible(false);
        
        

        //Criando Os RadiosButtons
        rbFisica = new JRadioButton("Pessoa Fisica");
        rbFisica.setBounds(9, 60, 150, 25);
        jpDadosBasicos.add(rbFisica);

        rbJuridica = new JRadioButton("Pessoa Juridica");
        rbJuridica.setBounds(9, 80, 150, 25);
        jpDadosBasicos.add(rbJuridica);

        //Agrupando os Radios Buttons
        bgTipoPessoa = new ButtonGroup();
        bgTipoPessoa.add(rbFisica);
        bgTipoPessoa.add(rbJuridica);
        
        //Açao dos RadiosButtons
        rbFisica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCamposFisica();
            }
        });
        
        rbJuridica.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCamposJuridica();
            }
        });
        
        //Selecionando pessoa FIsica como Padrao
        rbFisica.setSelected(true);
        mostrarCamposFisica();
        
    }
    
    //Metodo para exibir os campos Pessoa Fisica
    private void mostrarCamposFisica() {
        jlCPF.setVisible(true);
        jtfCPF.setVisible(true);
        jlNome.setVisible(true);
        jtfNome.setVisible(true);
        jlDataNascimento.setVisible(true);
        jtfDataNascimento.setVisible(true);
        
        
        //Ocutar campos de pessoa Juridica
        jlCNPJ.setVisible(false);
        jtfCNPJ.setVisible(false);
        jlNomeFantasia.setVisible(false);
        jtfNomeFantasia.setVisible(false);
        jlRazaoSocial.setVisible(false);
        jtfRazaoSocial.setVisible(false);
        jlResponsavel.setVisible(false);
        jtfResponsavel.setVisible(false);
    }
    
    //Metodo para exibir os campos Pessoa Fisica
    private void mostrarCamposJuridica() {
        jlCPF.setVisible(false);
        jtfCPF.setVisible(false);
        jlNome.setVisible(false);
        jtfNome.setVisible(false);
        jlDataNascimento.setVisible(false);
        jtfDataNascimento.setVisible(false);
        
        
        //Mostrar campos de pessoa Juridica
        jlCNPJ.setVisible(true);
        jtfCPF.setVisible(true);
        jlNomeFantasia.setVisible(true);
        jtfNomeFantasia.setVisible(true);
        jlRazaoSocial.setVisible(true);
        jtfRazaoSocial.setVisible(true);
        jlResponsavel.setVisible(true);
        jtfResponsavel.setVisible(true);
    }
    
//    //Metodo para validar e converter a data;
//    private Date obterDataNascimento() {
//        String txtData = jtfDataNascimento.getText().trim();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        sdf.setLenient(false); // funçao que impede datas invalidas
//        
//        try {
//            return sdf.parse(txtData);
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(this,
//                    "Data de nascimento inválida! Use o Formato DD/MM/AAAA",
//                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }
    

}
