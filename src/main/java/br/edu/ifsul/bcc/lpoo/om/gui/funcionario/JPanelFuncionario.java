package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author arturmariano
 */
public class JPanelFuncionario extends JPanel {
    
    private CardLayout cardLayout;
    private Controle  controle;
    
    private JPanelFuncionarioFormulario formulario;
    private JPanelFuncionarioListagem listagem;
    
    public JPanelFuncionario(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        formulario = new JPanelFuncionarioFormulario(getControle(), this);
        listagem = new JPanelFuncionarioListagem(getControle(), this);
        
        this.add(formulario, "tela_funcionario_formulario");
        this.add(listagem, "tela_funcionario_listagem");
                
    }
    
    public void showTela(String nomeTela){
        
        try{
            
            if(nomeTela.equals("tela_funcionario_listagem")){

                listagem.populaTable();

            }else if(nomeTela.equals("tela_funcionario_formulario")){

                getFormulario().populaComboCargo();

            }

            cardLayout.show(this, nomeTela);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Atenção", "Erro ao acessar dados : "+e.getLocalizedMessage(), JOptionPane.ERROR);
            e.printStackTrace();
        }
        

        
    }

    public JPanelFuncionarioFormulario getFormulario() {
        return formulario;
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }
    
    
}