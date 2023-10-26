package br.edu.ifsul.bcc.lpoo.om;

import br.edu.ifsul.bcc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenciaJDBC;

/**
 *
 * @author arturmariano
 */

public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    
    private JFramePrincipal jframe;
    
    public Controle(){
        
    }
    
    // teste para verificar se foi possível conectar no BD
    public boolean conectarBD() throws Exception{
        conexaoJDBC = new PersistenciaJDBC();
        if (getConexaoJDBC() != null){
            return getConexaoJDBC().conexaoAberta();
        }
        return false;
    }
    
    public PersistenciaJDBC getConexaoJDBC(){
        return conexaoJDBC;
    }
    
    public void initComponents(){
        jframe = new JFramePrincipal();     
        
        jframe.setVisible(true);    // torna visível o jframe
    }
}
