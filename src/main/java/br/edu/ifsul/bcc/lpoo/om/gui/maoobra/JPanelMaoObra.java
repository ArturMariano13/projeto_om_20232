/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.maoobra;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author arturmariano
 */
public class JPanelMaoObra extends JPanel  {
    
    private CardLayout cardLayout;
    private Controle  controle;

    private JPanelMaoObraFormulario formulario;
    private JPanelMaoObraListagem listagem;
    
    
    public JPanelMaoObra(Controle controle){
        this.controle = controle;
        
        initComponents();
    }
    
    private void initComponents(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
            
        formulario = new JPanelMaoObraFormulario(getControle(), this);
        listagem = new JPanelMaoObraListagem(getControle(), this);
            
        this.add(formulario, "tela_maoobra_formulario");
        this.add(listagem, "tela_maoobra_listagem");

    }
    
    public void showTela(String nomeTela){
        
        try{
            
            if(nomeTela.equals("tela_maoobra_listagem")){
                listagem.populaTabela("");

            }else if(nomeTela.equals("tela_maoobra_formulario")){
                
                getFormulario();

            }

            cardLayout.show(this, nomeTela);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Atenção", "Erro ao acessar dados : "+e.getLocalizedMessage(), JOptionPane.ERROR);
            e.printStackTrace();
        }
    
     }
    
    
    
    public JPanelMaoObraFormulario getFormulario() {
        return formulario;
    }
    
    public Controle getControle() {
        return controle;
    }
    
    
}
