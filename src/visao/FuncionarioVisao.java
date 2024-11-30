/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visao;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Gabriel Fernandes
 */
public class FuncionarioVisao extends FormPadrao {
    
    JLabel jlNome, jlCPF, jlTelefone, jlEndereco, jlDataEntrada, jlDataSaida, jlEmail;
    JTextField jtfNome, jtfCPF, jtfTelefone, jtfEndereco, jtfDataEntrada, jtfDataSaida, jtfEmail;

    public FuncionarioVisao() {
        setTitle("CADASTRO DE FUNCIONARIO");
        
        jlNome = new JLabel("Nome Completo:");
        jlNome.setBounds(2, 1, 100, 25);
        jpCamposDados.add(jlNome);
        
        jtfNome = new JTextField();
        jtfNome.setBounds(100, 1, 600, 25);
        jpCamposDados.add(jtfNome);
        
        jlCPF = new JLabel("CPF:");
        jlCPF.setBounds(2, 30, 50, 25);
        jpCamposDados.add(jlCPF);
        
        jtfCPF = new JTextField();
        jtfCPF.setBounds(40, 30, 200, 25);
        jpCamposDados.add(jtfCPF);
        
        jlTelefone = new JLabel("Telefone:");
        jlTelefone.setBounds(300, 30, 60, 25);
        jpCamposDados.add(jlTelefone);
        
        jtfTelefone = new JTextField();
        jtfTelefone.setBounds(360, 30, 250, 25);
        jpCamposDados.add(jtfTelefone);
        
        jlEndereco = new JLabel("Endereço:");
        jlEndereco.setBounds(2, 60, 150, 25);
        jpCamposDados.add(jlEndereco);
        
        jtfEndereco = new JTextField();
        jtfEndereco.setBounds(65, 60, 635, 25);
        jpCamposDados.add(jtfEndereco);
        
        jlDataEntrada = new JLabel("Data de Entrada:");
        jlDataEntrada.setBounds(2, 95, 100, 25);
        jpCamposDados.add(jlDataEntrada);
        
        jtfDataEntrada = new JTextField();
        jtfDataEntrada.setBounds(95, 95, 200, 25);
        jpCamposDados.add(jtfDataEntrada);
        
        jlDataSaida = new JLabel("Data de Saida:");
        jlDataSaida.setBounds(295, 95, 100, 25);
        jpCamposDados.add(jlDataSaida);
        
        jtfDataSaida = new JTextField();
        jtfDataSaida.setBounds(375, 95, 200, 25);
        jpCamposDados.add(jtfDataSaida);
        
        jlEmail = new JLabel("Email:");
        jlEmail.setBounds(2, 130, 100, 25);
        jpCamposDados.add(jlEmail);
        
        jtfEmail = new JTextField();
        jtfEmail.setBounds(35, 130, 665, 25);
        jpCamposDados.add(jtfEmail);
        
    }
    
}
