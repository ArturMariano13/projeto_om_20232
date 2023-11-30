package br.edu.ifsul.bcc.lpoo.om;

import br.edu.ifsul.bcc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.bcc.lpoo.om.gui.JMenuBarHome;
import br.edu.ifsul.bcc.lpoo.om.gui.JPanelHome;
import br.edu.ifsul.bcc.lpoo.om.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.bcc.lpoo.om.gui.funcionario.JPanelFuncionario;
import br.edu.ifsul.bcc.lpoo.om.gui.peca.JPanelPeca;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author arturmariano
 */

public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal jframe;
    private JPanelAutenticacao telaAutenticacao;
    private JPanelFuncionario telaFuncionario;
    private JMenuBarHome menuBar;
    private JPanelHome  telaHome;
    private JPanelPeca telaPeca;
    
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
        
        telaAutenticacao = new JPanelAutenticacao(this);
        
        // jframe.add(telaAutenticacao); // mostra a tela, porém funcionaria com apenas UMA tela
        // Devemos utilizar um gerenciador de telas (o CardLayout)
        
        menuBar = new JMenuBarHome(this);
        
        telaHome = new JPanelHome(this);
        
        telaFuncionario = new JPanelFuncionario(this);
        
        telaPeca = new JPanelPeca(this);
        
        jframe.addTela(telaAutenticacao, "tela_autenticacao"); //adiciona
        
        jframe.addTela(telaHome, "tela_home"); //adiciona
        
        jframe.addTela(telaFuncionario, "tela_funcionario");
        
        jframe.addTela(telaPeca, "tela_peca");
        
        jframe.showTela("tela_autenticacao");   //mostra
        
        jframe.setVisible(true);    // torna visível o jframe
    }
    
    public void autenticar (String cpf, String senha){
        
        try{
            
           Funcionario f = conexaoJDBC.doLogin(cpf, senha);
           
           if(f != null){

                JOptionPane.showMessageDialog(telaAutenticacao, "Funcionario "+f.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                 jframe.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                 jframe.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(telaAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(telaAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showTela(String nomeTela) throws Exception{
         
        //para cada nova tela de CRUD adicionar um elseif
        
        if(nomeTela.equals("tela_funcionario")){
             
            telaFuncionario.showTela("tela_funcionario_listagem");

        }else if (nomeTela.equals("tela_peca")){
            telaPeca.showTela("tela_peca_listagem");
        }
         
        jframe.showTela(nomeTela);
         
    }
        
        
}

