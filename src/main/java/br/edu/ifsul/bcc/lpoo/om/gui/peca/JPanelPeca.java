package br.edu.ifsul.bcc.lpoo.om.gui.peca;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;

/**
 *
 * @author arturmariano
 */
public class JPanelPeca extends javax.swing.JPanel {

    private JPanelPecaFormulario formulario;
    private JPanelPecaListagem listagem;
    private Controle controle;
    
    
    public JPanelPeca(Controle controle) {
        initComponents();
        this.controle = controle;
        formulario = new JPanelPecaFormulario(this, controle);
        listagem = new JPanelPecaListagem(this, controle);
        this.add(formulario, "tela_peca_formulario");
        this.add(listagem, "tela_peca_listagem");
    }
    
    public void showTela(String nomeTela) throws Exception{
        
        if(nomeTela.equals("tela_peca_listagem")){
            
            listagem.populaTabela("");
            
        }else if(nomeTela.equals("tela_peca_formulario")){
            
            //getFormulario().populaComboCargo();
            
        }
       
        ((CardLayout)this.getLayout()).show(this, nomeTela);
        // casting para converter o layout manager para CardLayout
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}