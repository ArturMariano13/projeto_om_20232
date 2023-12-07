package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arturmariano
 */
public class JPanelFuncionarioListagem extends JPanel implements ActionListener{
    
    // *** NORTE ***
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltro;
    private FlowLayout layoutFlowNorte;
    
    // *** CENTRO ***
    private JPanel pnlCentro;
    private JScrollPane scpCentro;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private BorderLayout layoutBorderCentro;
    
    // *** SUL ***
    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnRemover;
    private FlowLayout layoutFlowSul;
    
    private BorderLayout layoutBorder;
    
    public Controle controle;
    public JPanelFuncionario pnlFuncionario;
    
    private SimpleDateFormat format;
    
    
    public JPanelFuncionarioListagem(Controle controle, JPanelFuncionario pnlFuncionario) {
        this.controle = controle;
        this.pnlFuncionario = pnlFuncionario;
        
       initComponents();
    }
    
    private void initComponents(){
        
        layoutBorder = new BorderLayout();
        this.setLayout(layoutBorder);//seta o gerenciador border para este painel
        
        // *** PAINEL CENTRAL ***
        pnlCentro = new JPanel();
        layoutBorderCentro = new BorderLayout();
        pnlCentro.setLayout(layoutBorderCentro);
        tabela = new JTable();
        
        modeloTabela = new DefaultTableModel(
                new String[] {
                    "CPF", "Nome"}, 0);
        
        tabela.setModel(modeloTabela);
        
        scpCentro = new JScrollPane();
        scpCentro.setViewportView(tabela);
        pnlCentro.add(scpCentro, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        // *** PAINEL SUL ***
        pnlSul = new JPanel();
        layoutFlowSul = new FlowLayout();
        pnlSul.setLayout(layoutFlowSul);
        
        // *** BOTÃO NOVO ***
        btnNovo = new JButton("Novo");
        btnNovo.setActionCommand("comando_novo");
        btnNovo.addActionListener(this);
        btnNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSul.add(btnNovo);
        
        // *** BOTÃO EDITAR ***
        btnEditar = new JButton("Editar");
        btnEditar.setActionCommand("comando_editar");
        btnEditar.addActionListener(this);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSul.add(btnEditar);
        
        // *** BOTÃO REMOVER ***
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("comando_remover");
        btnRemover.addActionListener(this);     
        btnRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSul.add(btnRemover);
        
        this.add(pnlSul, BorderLayout.SOUTH);        
    }
    
    public void populaTable() throws Exception{
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

        Collection<Funcionario> listFuncionarios =  controle.getConexaoJDBC().listFuncionarios();
        for(Funcionario f : listFuncionarios){
                                
            model.addRow(new Object[]{f,    // chame o toString                                      
                                    f.getNome()});
            } 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    
        if(ae.getActionCommand().equals(btnNovo.getActionCommand())){
                
            pnlFuncionario.showTela("tela_funcionario_formulario");
            pnlFuncionario.getFormulario().setFuncionarioFormulario(null); //limpando o formulário.                        
            
        }else if(ae.getActionCommand().equals(btnEditar.getActionCommand())){
            int indice = tabela.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){
                DefaultTableModel model =  (DefaultTableModel) tabela.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Funcionario f = (Funcionario) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...
                pnlFuncionario.showTela("tela_funcionario_formulario");
                pnlFuncionario.getFormulario().setFuncionarioFormulario(f); 
                
            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }else if(ae.getActionCommand().equals(btnRemover.getActionCommand())){
            int indice = tabela.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){
                DefaultTableModel model =  (DefaultTableModel) tabela.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Funcionario f = (Funcionario) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlFuncionario.getControle().getConexaoJDBC().remover(f);
                    JOptionPane.showMessageDialog(this, "Funcionario removido!", "Funcionario", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Funcionario -:"+ex.getLocalizedMessage(), "Funcionario", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }                        

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }  
}
