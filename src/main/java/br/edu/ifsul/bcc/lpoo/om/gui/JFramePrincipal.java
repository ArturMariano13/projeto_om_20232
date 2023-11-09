package br.edu.ifsul.bcc.lpoo.om.gui;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arturmariano
 */
public class JFramePrincipal extends JFrame {

    private Controle controle;
    private CardLayout cardLayout;
    private JPanel painel;

    public JFramePrincipal() {

        initComponents();
    }

    private void initComponents() {

        // definir o título do jframe
        this.setTitle("Sisteminha para CRUD - Oficina Mecânica");

        // definir o tamanho mínimo
        this.setMinimumSize(new Dimension(600, 600));

        // definir a abertura em modo maximizado
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // definir o comportamento de fechar o processo no fechamento do JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // finaliza o processo quando o frame é fechado

        cardLayout = new CardLayout();//iniciando o gerenciador de layout para esta JFrame
        painel = new JPanel();//inicializacao
        painel.setLayout(cardLayout);//definindo o cardLayout para o paineldeFundo
        this.add(painel);  //adiciona no JFrame o paineldeFundo

    }

    public void addTela(JPanel p, String nome) {

        painel.add(p, nome); // adiciona uma "carta no baralho".
    }

    public void showTela(String nome) {

        cardLayout.show(painel, nome); // localiza a "carta no baralho" e mostra.
    }

}
