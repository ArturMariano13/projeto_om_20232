package br.edu.ifsul.bcc.lpoo.om.gui.autenticacao;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author arturmariano
 */

public class JPanelAutenticacao extends JPanel implements ActionListener{
    
    private Controle controle;
    private JLabel lblCPF;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
    private GridBagLayout gridLayout;   // gerenciador de layout
    private GridBagConstraints posicionador;
    
    public JPanelAutenticacao(Controle controle){
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
       
        this.setBackground(new Color(240, 240, 240));
        
        
        
        gridLayout = new GridBagLayout();   // inicialização do gerenciador de layout
        this.setLayout(gridLayout);     // define o gerenciador desse painel
        
        lblCPF = new JLabel("CPF: ");
        lblCPF.setToolTipText("lblCPF");    // acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;     // posição da coluna (vertical)
        posicionador.gridx = 0;     // posição da linha (horizontal)
        lblCPF.setFont(new Font("Serif", Font.BOLD, 14));      
        this.add(lblCPF, posicionador);     // o "add" adiciona o rótulo no painel
        
        
        
        txfCPF = new JTextField(10);
        txfCPF.setFocusable(true);      // acessibilidade -> já sai selecionado
        txfCPF.setToolTipText("txtCPF");    // acessibilidade
        //Util.considerarEnterComoTab(txfCPF);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;     // posição da coluna (vertical)
        posicionador.gridx = 1;     // posição da linha (horizontal)
        
        txfCPF.setFont(new Font("Serif", Font.PLAIN, 12));
        txfCPF.setForeground(new Color(30, 30, 30)); // Cor do texto
        txfCPF.setBackground(new Color(213, 253, 224)); // Cor de fundo
        // Adiciona uma borda ao redor do campo de texto
        Border textFieldBorder = BorderFactory.createLineBorder(new Color(100, 100, 100), 1);
        txfCPF.setBorder(textFieldBorder);
        
        defaultBorder = txfCPF.getBorder();
        this.add(txfCPF, posicionador);
        
        lblSenha = new JLabel("Senha: ");
        lblSenha.setToolTipText("lblSenha");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        lblSenha.setFont(new Font("Serif", Font.BOLD, 14));      
        this.add(lblSenha, posicionador);
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);
        psfSenha.setToolTipText("psfSenha");
        psfSenha.setBorder(textFieldBorder);
        //Util.considerarEnterComoTab(psfSenha);
        psfSenha.setFont(new Font("Serif", Font.PLAIN, 12));
        psfSenha.setForeground(new Color(30,30,30));
        psfSenha.setBackground(new Color(213,253,224));
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        this.add(psfSenha, posicionador);
        
        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);
        btnLogar.setToolTipText("btnLogar");
        btnLogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da coluna (vertical)
        posicionador.gridx = 1;// posição da linha (horizontal)
        btnLogar.addActionListener(this);   // registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        // validação do formulário (cpf e senha)
        // acionar o método para realizar a autenticação
        
        System.out.println("Clicou no botão: " + ae.getActionCommand());

        //testa para verificar se o botão btnLogar foi clicado.
        if(ae.getActionCommand().equals(btnLogar.getActionCommand())){

            //validacao do formulario.
            if(txfCPF.getText().trim().length() == 11){

                txfCPF.setBorder(new LineBorder(Color.green,1));

                if(new String(psfSenha.getPassword()).trim().length() >= 3 ){

                    psfSenha.setBorder(new LineBorder(Color.green,1));

                    controle.autenticar(txfCPF.getText().trim(), new String(psfSenha.getPassword()).trim());

                }else {

                    JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red,1));
                    psfSenha.requestFocus();                        

                }

            }else{

                JOptionPane.showMessageDialog(this, "Informe CPf com 11 dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                txfCPF.setBorder(new LineBorder(new Color(255,0,0)));
                txfCPF.requestFocus();
            }
                                      
            
        }
    }
    
}
