package br.edu.ifsul.bcc.lpoo.om.gui.autenticacao;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextField txtCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
    private GridBagLayout gridLayout;   // gerenciador de layout
    private GridBagConstraints posicionador;
    
    public JPanelAutenticacao(){
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
       
        gridLayout = new GridBagLayout();   // inicialização do gerenciador de layout
        this.setLayout(gridLayout);     // define o gerenciador desse painel
        
        lblCPF = new JLabel("CPF: ");
        lblCPF.setToolTipText("lblCPF");    // acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;     // posição da linha (vertical)
        posicionador.gridx = 0;     // posição da coluna (horizontal)
        this.add(lblCPF, posicionador);     // o "add" adiciona o rótulo no painel
        
        txtCPF = new JTextField(10);
        txtCPF.setFocusable(true);      // acessibilidade
        txtCPF.setToolTipText("txtCPF");    // acessibilidade
        //Util.considerarEnterComoTab(txfCPF);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;     // posição da linha (vertical)
        posicionador.gridx = 1;     // posição da coluna (horizontal)
        defaultBorder = txtCPF.getBorder();
        this.add(txtCPF, posicionador);
        
        lblSenha = new JLabel("Senha: ");
        lblSenha.setToolTipText("lblSenha");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        this.add(lblSenha, posicionador);
        
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);
        psfSenha.setToolTipText("psfSenha");
        //Util.considerarEnterComoTab(psfSenha);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        this.add(psfSenha, posicionador);
        
        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);
        btnLogar.setToolTipText("btnLogar");
        //Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);   // registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //testa para verificar se o botão btnLogar foi clicado.
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){

            //validacao do formulario.
            if(txtCPF.getText().trim().length() == 11){

                txtCPF.setBorder(new LineBorder(Color.green,1));

                if(new String(psfSenha.getPassword()).trim().length() >= 3 ){

                    psfSenha.setBorder(new LineBorder(Color.green,1));

                    controle.autenticar(txtCPF.getText().trim(), new String(psfSenha.getPassword()).trim());

                }else {

                    JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red,1));
                    psfSenha.requestFocus();                        

                }

            }else{

                JOptionPane.showMessageDialog(this, "Informe CPf com 11 dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                txtCPF.setBorder(new LineBorder(Color.red,1));
                txtCPF.requestFocus();
            }
                                      
            
        } 
    }
    
}
