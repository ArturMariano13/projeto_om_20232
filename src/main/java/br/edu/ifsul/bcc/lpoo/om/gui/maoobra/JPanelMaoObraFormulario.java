/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.maoobra;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenciaJDBC;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author arturmariano
 */
public class JPanelMaoObraFormulario extends JPanel implements ActionListener{
    
   private Controle controle;
   private JPanelMaoObra pnlMaoObra;
   
    private BorderLayout borderLayout;
    private JPanel pnlCentro;
   
   private GridBagLayout gridBagLayout;
   private GridBagConstraints posicionador;
   
   private JLabel lblId;
   private JTextField txfId;
   
   private JLabel lblDescricao;
   private JTextField txfDescricao;
   
   private JLabel lblTempo_estimado;
   private JTextField txfTempo_estimado;
   
   private JLabel lblValor;
   private JTextField txfValor;
    
    private JPanel pnlSul;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    private PersistenciaJDBC jdbc;
    
    public JPanelMaoObraFormulario(Controle controle, JPanelMaoObra pnlMaoObra){
        this.controle = controle;
        this.pnlMaoObra = pnlMaoObra;
        
        InitComponents();
    }
    
    private void InitComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlCentro = new JPanel();
        gridBagLayout = new GridBagLayout();
        pnlCentro.setLayout(gridBagLayout);
        
        lblId = new JLabel("ID: ");
        txfId = new JTextField(5);
        
        lblDescricao = new JLabel("Descrição: ");
        txfDescricao = new JTextField(20);
        
        lblTempo_estimado = new JLabel("Tempo estimado: ");
        txfTempo_estimado = new JTextField(20);
        
        lblValor = new JLabel("Valor: ");
        txfValor = new JTextField(10);
        
        // *** ID ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblId, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfId, posicionador);//o add adiciona o rotulo no painel  
        
        // *** DESCRIÇÃO ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblDescricao, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfDescricao, posicionador);//o add adiciona o rotulo no painel  
        
        // *** TEMPO ESTIMADO ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblTempo_estimado, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfTempo_estimado, posicionador);//o add adiciona o rotulo no painel  

        // *** VALOR ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlCentro.add(lblValor, posicionador);//o add adiciona o rotulo no painel  
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfValor, posicionador);//o add adiciona o rotulo no painel 
        
        this.add(pnlCentro, BorderLayout.CENTER); 
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnSalvar = new JButton("Salvar");
        btnSalvar.setActionCommand("botao_salvar");
        btnSalvar.addActionListener(this);
        btnSalvar.setFocusable(true);    //acessibilidade    
        btnSalvar.setToolTipText("btnGravarFuncionario"); //acessibilidade
        btnSalvar.setMnemonic(KeyEvent.VK_G);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setActionCommand("botao_cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarFuncionario"); //acessibilidade
        btnCancelar.setMnemonic(KeyEvent.VK_C);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pnlSul.add(btnSalvar);
        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);
    }
    public void setMaoObraFormulario(MaoObra m) throws Exception{
        if(m == null){
            jdbc = new PersistenciaJDBC();
            Integer id = jdbc.maiorCodigoMaoObra();
            txfId.setText(id.toString());
            txfId.setEditable(false);
            txfDescricao.setText("");
            txfTempo_estimado.setText("");
            txfValor.setText("");
        }else{
            txfId.setText(m.getId().toString());
            txfId.setEditable(false);
            txfDescricao.setText(m.getDescricao());
            txfTempo_estimado.setText(m.getTempo_estimado_execucao().toString());
            txfValor.setText(m.getValor().toString());
                    
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals(btnSalvar.getActionCommand())) {

            MaoObra m = null;
            try {
                m = getMaoObraFormulario(); //recupera os dados do formulario
            } catch (Exception ex) {
                Logger.getLogger(JPanelMaoObraFormulario.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (m != null) {

                try {
                    pnlMaoObra.getControle().getConexaoJDBC().persist(m);

                    JOptionPane.showMessageDialog(this, "Mao de Obra armazenada!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlMaoObra.showTela("tela_maoobra_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Mao de Obra! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } else {

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (ae.getActionCommand().equals(btnCancelar.getActionCommand())) {

                pnlMaoObra.showTela("tela_maoobra_listagem");
        }
    }

    private MaoObra getMaoObraFormulario() throws Exception {
        if(txfId.getText().trim().length() > 0 &&
           txfDescricao.getText().trim().length() > 0  &&
           txfValor.getText().trim().length() > 0 &&
            (txfTempo_estimado.getText().trim().length() == 8 || txfTempo_estimado.getText().trim().length() == 0)){
            
            MaoObra m = new MaoObra();
            m.setDescricao(txfDescricao.getText().trim());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            if (txfTempo_estimado.getText().trim().length() == 8){
                m.setTempo_estimado_execucao(sdf.parse(txfTempo_estimado.getText().trim()));
            }else{
                m.setTempo_estimado_execucao(null);
            }
            m.setValor(Float.parseFloat(txfValor.getText().trim()));
            return m;   
        }
        return null;
    }
}
    


