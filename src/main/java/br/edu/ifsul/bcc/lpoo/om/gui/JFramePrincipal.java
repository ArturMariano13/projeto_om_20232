package br.edu.ifsul.bcc.lpoo.om.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author arturmariano
 */


public class JFramePrincipal extends JFrame{
    
    public JFramePrincipal(){
        
        initComponents();
    }
    
    
    private void initComponents(){
        
        // definir o título do jframe
        this.setTitle("Sisteminha para CRUD - Oficina Mecânica");  
        
        // definir o tamanho mínimo
        this.setMinimumSize(new Dimension(600,600));
        
        // definir a abertura em modo maximizado
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // definir o comportamento de fechar o processo no fechamento do JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // finaliza o processo quando o frame é fechado
        
    }
}
