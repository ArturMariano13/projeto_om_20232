/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.maoobra;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.gui.peca.JPanelPecaListagem;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arturmariano
 */
public class JPanelMaoObraListagem extends JPanel implements ActionListener {
    
   private Controle controle;
   private JPanelMaoObra pnlMaoObra;
   
   private JPanel pnlNorte;
   private JLabel lblFiltro;
   private JTextField txfFiltro;
   private JButton btnFiltro;
   private FlowLayout layoutFlowNorte;
   
   private JPanel pnlCentro;
   private JScrollPane scpCentro;
   private DefaultTableModel tableModel;
   private JTable table;
   private BorderLayout borderLayoutCentro;
   
   private JPanel pnlSul;
   private JButton btnNovo;
   private JButton btnEditar;
   private JButton btnRemover;
   private FlowLayout layoutFlowSul;
   
   private BorderLayout borderLayout;


    
    
    public JPanelMaoObraListagem(Controle controle, JPanelMaoObra pnlMaoObra){
        this.controle = controle;
        this.pnlMaoObra = pnlMaoObra;
        
        InitComponents();
    }
    
    private void InitComponents(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());
        lblFiltro = new JLabel("Filtrar por Descrição");
        txfFiltro = new JTextField(15);
        
        btnFiltro = new JButton("Filtrar");
        btnFiltro.setActionCommand("botao_filtrar");
        btnFiltro.addActionListener(this);
        pnlNorte.add(lblFiltro);
        pnlNorte.add(txfFiltro);
        pnlNorte.add(btnFiltro);
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        pnlCentro = new JPanel();
        borderLayoutCentro = new BorderLayout();
        pnlCentro.setLayout(borderLayoutCentro);
        
        table = new JTable();
        tableModel = new DefaultTableModel(
        new String[]{
        "Código", "Descrição","Valor"},0);
        table.setModel(tableModel);
        
        scpCentro = new JScrollPane();
        scpCentro.setViewportView(table);
        pnlCentro.add(scpCentro, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlSul = new JPanel();
        layoutFlowSul = new FlowLayout();
        pnlSul.setLayout(layoutFlowSul);
        
        btnNovo = new JButton("Novo");
        btnNovo.setActionCommand("comando_novo");
        btnNovo.addActionListener(this);
        pnlSul.add(btnNovo);
        
        btnEditar = new JButton("Editar");
        btnEditar.setActionCommand("comando_editar");
        btnEditar.addActionListener(this);
        pnlSul.add(btnEditar);
        
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("comando_remover");
        btnRemover.addActionListener(this);
        pnlSul.add(btnRemover);
        
        this.add(pnlSul, BorderLayout.SOUTH);
    }

    public void populaTabela(String filtro) throws Exception{
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        
        Collection<MaoObra> listMaoObra = controle.getConexaoJDBC().listMaoObras(filtro);
        for(MaoObra m : listMaoObra){
            
            model.addRow(new Object[]{m,
                m.getDescricao(),
                m.getValor()
            });
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals(btnNovo.getActionCommand())){
            
            pnlMaoObra.showTela("tela_maoobra_formulario");                        
            try {
                pnlMaoObra.getFormulario().setMaoObraFormulario(null); //limpando o formulário.                        
            } catch (Exception ex) {
                Logger.getLogger(JPanelMaoObraListagem.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if (ae.getActionCommand().equals(btnEditar.getActionCommand())){
            int indice = table.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) table.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                MaoObra m = (MaoObra) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                pnlMaoObra.showTela("tela_maoobra_formulario");
                try { 
                    pnlMaoObra.getFormulario().setMaoObraFormulario(m);
                } catch (Exception ex) {
                    Logger.getLogger(JPanelMaoObraListagem.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(ae.getActionCommand().equals(btnRemover.getActionCommand())){
            
            int indice = table.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) table.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                MaoObra f = (MaoObra) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlMaoObra.getControle().getConexaoJDBC().remover(f);
                    JOptionPane.showMessageDialog(this, "Mão de Obra removida!", "Mao de Obra", JOptionPane.INFORMATION_MESSAGE);
                    populaTabela(""); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Mão de Obra -:"+ex.getLocalizedMessage(), "Mao de Obra", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }                        

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(ae.getActionCommand().equals(btnFiltro.getActionCommand())){
            
            String filtro = txfFiltro.getText().trim();
        
        try {
            populaTabela(filtro);
        } catch (Exception ex) {
            System.out.println("Erro");
            Logger.getLogger(JPanelPecaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
}
