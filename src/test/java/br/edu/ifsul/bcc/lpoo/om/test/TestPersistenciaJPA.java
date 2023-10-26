package br.edu.ifsul.bcc.lpoo.om.test;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Veiculo;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author arturmariano
 */
public class TestPersistenciaJPA {

    //@Test
    public void testConexaoJPA() {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if (jpa.conexaoAberta()) {
            System.out.println("conectou no BD via jpa ...");
            jpa.fecharConexao();
        } else {
            System.out.println("nao conectou no BD ...");

        }
    }

    //@Test
    public void testPersistenciaCargoJPA() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if (jpa.conexaoAberta()) {

            //Passo 1: encontrar o cargo id = 1
            Cargo cg = (Cargo) jpa.find(Cargo.class, new Integer("1"));
            if (cg != null) {
                //Passo 2: caso encontre, imprimir o id e a descricao, alterar e remover.
                System.out.println("Encontrou o cargo id=" + cg.getId() + " - " + cg.getDescricao());
                cg.setDescricao("descricao do cargo alterada");
                jpa.persist(cg);
                System.out.println("\tDescricao alterada: " + cg.getDescricao());
                jpa.remover(cg);
                System.out.println("\tRemoveu o cargo id:" + cg.getId());
            } else {
                //Passo 3: caso não encontre persistir um novo cargo.
                System.out.println("Não encontro o cargo, criando um novo");
                cg = new Cargo();
                cg.setDescricao("Cargo de teste");
                jpa.persist(cg);

            }

            System.out.println("testPersistenciaCargoJPA:: conectou no BD via jpa ...");
            jpa.fecharConexao();
        } else {
            System.out.println("nao conectou no BD ...");

        }
    }

    //@Test
    public void testPersistenciaListCargoJPA() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if (jpa.conexaoAberta()) {
            //Passo 1: recuperar a coleção de cargos.
            //Passo 2: caso a coleção não esteja vazia - imprimir, alterar e remover cada item.
            //Passo 3: caso a coleção esteja vazia, criar dois cargos.          
            System.out.println("testPersistenciaListCargoJPA:: conectou no BD via jpa ...");
            jpa.fecharConexao();
        } else {
            System.out.println("nao conectou no BD ...");
        }
    }

    //Exercício 31/08
    /*
       Criar um método de teste para funcionario
         Passo 1: recuperar a coleção de funcionarios.
         Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos), 
                  alterar e remover cada item.
         Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um Curso cada.
     */

    @Test
    public void testInsereVeiculos() throws Exception {

        PersistenciaJPA jpa = new PersistenciaJPA();
        if (jpa.conexaoAberta()) {
            System.out.println("testPersistenciaListCargoJPA:: conectou no BD via jpa ...");

                Veiculo v = new Veiculo();
                v.setPlaca("IWI9J98");
                v.setModelo("Fox");
                jpa.persist(v);

                v = new Veiculo();
                v.setPlaca("ITP6140");
                v.setModelo("Idea");
                jpa.persist(v);

                v = new Veiculo();
                v.setPlaca("JAA1E39");
                v.setModelo("Jetta");
                jpa.persist(v);
                
                v = new Veiculo();
                v.setPlaca("JPA1234");
                v.setModelo("Corolla");
                jpa.persist(v);
     

            jpa.fecharConexao();
        } else {
            System.out.println("Não conectou no BD...");
        }
    }

}
