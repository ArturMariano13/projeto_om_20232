package br.edu.ifsul.bcc.lpoo.om;

import javax.swing.JOptionPane;

/**
 *
 * @author arturmariano
 */
public class ProjetoOM_LPOO_20232 {

    private Controle controle;  // atributo do tipo Controle

    public ProjetoOM_LPOO_20232() {
        try {
            controle = new Controle();

            if (controle.conectarBD()) {
                // inicialização do objeto do tipo Controle
                controle.initComponents();
            } else {

                JOptionPane.showMessageDialog(null, "Não conectou no BD!",
                        " Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no BD! " +
                    "Banco de Dados: " + ex.getLocalizedMessage(),
                    "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ProjetoOM_LPOO_20232();     // criação de uma instância

    }
}
