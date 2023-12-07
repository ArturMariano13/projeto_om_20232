package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author arturmariano
 */
public class JPanelFuncionarioFormulario extends JPanel implements ActionListener {
    
    private BorderLayout borderLayout;
    private JPanel pnlCentro;
    
    private GridBagLayout gridBagLayout;
    private GridBagConstraints posicionador;
    
    private Controle controle;
    private JPanelFuncionario pnlFuncionario;
    
    private JLabel lblCPF;
    private JTextField txfCPF;
    
    private JLabel lblNome;
    private JTextField txfNome;
    
    private JLabel lblSenha;
    private JPasswordField psfSenha;
    
    private JLabel lblNumero_ctps; 
    private JTextField txfNumero_ctps;
    
    private JLabel lblDataAdmissao;
    private JTextField txfDataAdmissao;
    
    private JLabel lblDataNascimento;
    private JTextField txfDataNascimento;
    
    private JLabel lblCargo;
    private JComboBox cbxCargo;
    private DefaultComboBoxModel modeloComboCargo;
    
    private JPanel pnlSul;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    private JTabbedPane tbpAbas;
    
    private SimpleDateFormat format;


    public JPanelFuncionarioFormulario(Controle controle, JPanelFuncionario pnlFuncionario) {
        this.controle = controle;
        this.pnlFuncionario = pnlFuncionario;
        
        format = new SimpleDateFormat("dd/MM/yyyy");

        initComponents();
    }

    public void populaComboCargo() throws Exception {
        cbxCargo.removeAllItems();//zera o combo
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxCargo.getModel();
        model.addElement("Selecione"); //primeiro item      
        model.addAll(controle.getConexaoJDBC().listCargos());   // adiciona todos os cargos
    }

    public Funcionario getFuncionariobyFormulario() {
        //validacao do formulario
        if (txfCPF.getText().trim().length() == 11
                && txfNome.getText().trim().length() > 0
                && new String(psfSenha.getPassword()).trim().length() > 3
                && txfNumero_ctps.getText().trim().length() > 3
                && cbxCargo.getSelectedIndex() > 0) {

            Funcionario f = new Funcionario();
            f.setCpf(txfCPF.getText().trim());
            f.setSenha(new String(psfSenha.getPassword()).trim());
            f.setCargo((Cargo) cbxCargo.getSelectedItem());
            f.setNumero_ctps(txfNumero_ctps.getText().trim());
            f.setNome(txfNome.getText().trim());
            //f.setData_admmissao((Calendar)null);
            
            /*if (txfDataAdmissao.getText().trim().length() > 0) {
                try {
                    // Cria uma instância do SimpleDateFormat com o padrão desejado
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    // Converte a string de data para um objeto Date
                    Date dataAdmissao = dateFormat.parse(txfDataAdmissao.getText().trim());

                    // Cria um objeto Calendar e configura a data de admissão nele
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataAdmissao);

                    // Configura a data de admissão no objeto f
                    f.setData_admmissao(calendar);

                } catch (ParseException e) {
                    // Trate a exceção se a string de data não estiver no formato esperado
                    e.printStackTrace();
                }
            }*/
            if (txfDataNascimento.getText().trim().length() > 0) {
                try {
                    // Cria uma instância do SimpleDateFormat com o padrão desejado
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    // Converte a string de data para um objeto Date
                    Date dataNascimento = dateFormat.parse(txfDataNascimento.getText().trim());

                    // Cria um objeto Calendar e configura a data de admissão nele
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataNascimento);

                    // Configura a data de admissão no objeto f
                    f.setData_nascimento(calendar);

                } catch (ParseException e) {
                    // Trate a exceção se a string de data não estiver no formato esperado
                    e.printStackTrace();
                }
            }

            return f;
        }
        return null;
    }

    public void setFuncionarioFormulario(Funcionario f) {
        if (f == null) {//se o parametro estiver nulo, limpa o formulario
            txfCPF.setText("");
            txfNome.setText("");
            psfSenha.setText("");
            txfNumero_ctps.setText("");
            Calendar c = Calendar.getInstance();
            txfDataAdmissao.setText((String)format.format(c.getTime()));
            txfDataAdmissao.setEditable(false);
            txfDataNascimento.setText("");
            cbxCargo.setSelectedIndex(0);
        } else {
            txfCPF.setText(f.getCpf());
            txfCPF.setEditable(false);
            txfNome.setText(f.getNome());
            psfSenha.setText(f.getSenha());
            txfNumero_ctps.setText(f.getNumero_ctps());
            txfDataAdmissao.setText(format.format(f.getData_admmissao().getTime()));
            txfDataAdmissao.setEditable(false);
            txfDataNascimento.setText(format.format(f.getData_admmissao().getTime()));
            cbxCargo.getModel().setSelectedItem(f.getCargo());//aqui chama o método equals do classe Endereco
        }

    }

    private void initComponents() {

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlCentro = new JPanel();
        gridBagLayout = new GridBagLayout();
        pnlCentro.setLayout(gridBagLayout);
        
        lblCPF = new JLabel("CPF:");
        txfCPF = new JTextField(20);
        
        lblNome = new JLabel("Nome:");
        txfNome = new JTextField(30);
    
        lblSenha = new JLabel("Senha:");
        psfSenha = new JPasswordField(10);
    
        lblNumero_ctps = new JLabel("Numero da CTPS:"); 
        txfNumero_ctps = new JTextField(10);
    
        lblDataAdmissao = new JLabel("Data de Admissão:");
        txfDataAdmissao = new JTextField(10);
        
        lblDataNascimento = new JLabel("Data de Nascimento:");
        txfDataNascimento = new JTextField(10);
    
        lblCargo = new JLabel("Cargo:");
        cbxCargo = new JComboBox();    
        modeloComboCargo = new DefaultComboBoxModel(new Object[]{"Selecione"});
        cbxCargo.setModel(modeloComboCargo);
        
        // *** CPF ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblCPF, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfCPF, posicionador);//o add adiciona o rotulo no painel  
        
        // *** NOME ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblNome, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfNome, posicionador);//o add adiciona o rotulo no painel  
        
        // *** SENHA ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END; // ancoragem à direita
        pnlCentro.add(lblSenha, posicionador);//o add adiciona o rotulo no painel  

        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(psfSenha, posicionador);//o add adiciona o rotulo no painel  

        // *** NUMERO CTPS ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//posição da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlCentro.add(lblNumero_ctps, posicionador);//o add adiciona o rotulo no painel  
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(txfNumero_ctps, posicionador);//o add adiciona o rotulo no painel 
        
        // *** DATA ADMISSAO ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblDataAdmissao, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfDataAdmissao, posicionador); 
        
        // *** DATA NASCIMENTO ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblDataNascimento, posicionador);
 
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfDataNascimento, posicionador);
        
        // *** CARGO ***
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblCargo, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//posição da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlCentro.add(cbxCargo, posicionador);//o add adiciona o rotulo no painel  
        
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

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals(btnSalvar.getActionCommand())) {

            Funcionario f = getFuncionariobyFormulario();//recupera os dados do formulario

            if (f != null) {

                try {

                    pnlFuncionario.getControle().getConexaoJDBC().persist(f);

                    JOptionPane.showMessageDialog(this, "Funcionario armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlFuncionario.showTela("tela_funcionario_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionario! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } else {

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (ae.getActionCommand().equals(btnCancelar.getActionCommand())) {

                pnlFuncionario.showTela("tela_funcionario_listagem");
        }
    }
}
