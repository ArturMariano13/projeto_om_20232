package br.edu.ifsul.bcc.lpoo.om.gui.autenticacao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author arturmariano
 */
public class JPanelAutenticacao extends JPanel{
    
    private JLabel lblCPF;
    private JLabel lblSenha;
    private JTextField txtCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
    private GridBagLayout gridLayout;   // gerenciador de layout
    private GridBagConstraints posicionador;
    
    public JPanelAutenticacao(){
        initComponents();
    }
    
    private void initComponents(){
       
    }
    
}
