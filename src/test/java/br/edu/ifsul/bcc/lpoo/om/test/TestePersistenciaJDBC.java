
package br.edu.ifsul.bcc.lpoo.om.test;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Equipe;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.Orcamento;
import br.edu.ifsul.bcc.lpoo.om.model.Pagamento;
import br.edu.ifsul.bcc.lpoo.om.model.Peca;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import br.edu.ifsul.bcc.lpoo.om.model.StatusServico;
import br.edu.ifsul.bcc.lpoo.om.model.Veiculo;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenciaJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.junit.Test;

/**
 *
 * @author telmo
 */
public class TestePersistenciaJDBC {
    
    //implementar um metodo de teste para abrir e fechar uma conexao
    //usando apenas o jdbc.
    //@Test
    public void testConexaoJDBC() throws Exception{
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jdbc ...");
            jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD via jdbc ...");
                        
        }
    }
    /*
        implementar o seguinte teste
        1) recuperar todos os cargos
        2) se existir ao menos um cargo, imprimir, alterar e remover.
        3) se não existir, criar dois cargos e imprimi.
    */
    
    //@Test
    public void testPersistenciaListCargoJDBC() throws Exception {
        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){      
          System.out.println("testPersistenciaListCargoJDBC:: conectou no BD via jdbc ...");
          //Passo 1: recuperar a coleção de cargos.
          Collection<Cargo> cll = jdbc.listCargos();
          if(!cll.isEmpty()){
            //Passo 2: caso a coleção não esteja vazia - imprimir, alterar e remover cada item.
            for(Cargo c : cll){
                System.out.println("Cargo : "+c.getDescricao());
                c.setDescricao("descricao alterada");
                jdbc.persist(c);//alterar no banco.
                //remove no bd
                jdbc.remover(c);
            }    
          }else{
              //Passo 3: caso a coleção esteja vazia, criar dois cargos.
              Cargo c = new Cargo();
              c.setDescricao("teste");
              jdbc.persist(c);
              System.out.println("Cadastrou o cargo :"+c.getId()+" : "+c.getDescricao());
          }
                              
          jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");                        
        }
    }
    
        //Exercício 28/09
    /*
       Criar um método de teste para funcionario
         Passo 1: recuperar a coleção de funcionarios.
         Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos), 
                  alterar e remover cada item.
         Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um Curso cada.
    */ 
    
    //@Test
    public void testPersistenciaCargoJDBC() throws Exception {
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            
            //Passo 1: encontrar o cargo id = 1
            Cargo cg = (Cargo) jdbc.find(Cargo.class, Integer.valueOf("1"));
            if(cg != null){
                System.out.println("Cargo encontrado! ");
                System.out.println("ID: "+cg.getId()+"\nDescricao: "+cg.getDescricao());
            }else{
                 System.out.println("Não encontro o cargo");
            }
        }else{
            System.out.println("nao conectou no BD via jdbc ...");
            //atribuir uma instancia para o cg
            //setar a descricao
            //persistir no banco de dados.
        }
    }
    
    //@Test
    public void testServico() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            // TESTE EQUIPE
            Equipe e = new Equipe();
            e.setId(1);
            e.setEspecialidades("teste");
            e.setNome("apertador de parafuso");
            jdbc.persist(e);
            
            Servico s = new Servico();
            s.setEquipe(e);
            
            Orcamento o = new Orcamento();
            o.setObservacoes("teste");
            
            Cliente cli = new Cliente();
            cli.setCpf("3");
            cli.setNome("Artur");
            cli.setSenha("11111");
            jdbc.persist(cli);
            o.setCliente(cli);
            
            Funcionario func = new Funcionario();
            func.setCpf("1111111111");
            func.setNumero_ctps("1234");
            func.setNome("Toninho");
            func.setSenha("123456");
            Cargo cargo = new Cargo();
            cargo.setId(1);
            func.setCargo(cargo);
            jdbc.persist(func);
            o.setFuncionario(func);
            
            Veiculo v = new Veiculo();
            v.setPlaca("IWI9J98");
            v.setAno(2010);
            o.setVeiculo(v);
            jdbc.persist(o);
            
            s.setOrcamento(o);
            s.setValor(1245.0f);
            s.setStatus(StatusServico.ATRASADO);
            jdbc.persist(s);

           
            System.out.println("Persistiu");
        }
        else{
            System.out.println("Não conectou!");
        }
    }
    
    //@Test
    public void testCurso() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            Curso c = new Curso();
            //c.setId(1);
            c.setDescricao("nova");
            jdbc.persist(c);
           
            System.out.println("Persistiu");
        }
        else{
            System.out.println("Não conectou!");
        }
    }
    
    //@Test
    public void testMaoObra() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            MaoObra m = new MaoObra();
            m.setDescricao("teste");
            m.setValor(100.0f);
            Date dataEspecifica = new Date(123, 0, 1, 14, 30, 0);
            m.setTempo_estimado_execucao(dataEspecifica);
            jdbc.persist(m);
           
            System.out.println("Persistiu");
        }
        else{
            System.out.println("Não conectou!");
        }
    }
    
    @Test
    public void testOrcamento() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            Orcamento o = new Orcamento();
            Cliente cli = new Cliente();
            cli.setCpf("2");
            o.setCliente(cli);
            Funcionario func = new Funcionario();
            func.setCpf("1233");
            o.setFuncionario(func);
            MaoObra m = new MaoObra();
            m.setId("1");
            Collection<MaoObra> maos = new ArrayList();
            maos.add(m);
            o.setMaoobras(maos);
            Peca p = new Peca();
            p.setId(1);
            Collection<Peca> pecas = new ArrayList();
            pecas.add(p);
            o.setPecas(pecas);
            Veiculo v = new Veiculo();
            v.setPlaca("IWI9J98");
            o.setVeiculo(v);
            jdbc.persist(o);
            
            
        }
    }
    
    //@Test
    public void testCargo() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            Cargo c = new Cargo();
            c.setDescricao("teste");
            jdbc.persist(c);
           
            System.out.println("Persistiu");
        }
        else{
            System.out.println("Não conectou!");
        }
    }
    
    //@Test
    public void testPeca() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            Peca p = new Peca();
            //p.setId(1);
            p.setFornecedor("Pirelli");
            p.setNome("Roda aro 12");
            p.setValor(250.0f);
            jdbc.persist(p);
           
            System.out.println("Persistiu");
        }
        else{
            System.out.println("Não conectou!");
        }
    }
    
    //@Test
    public void testPagamento() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if (jdbc.conexaoAberta()){
            
            Pagamento p = new Pagamento();
            p.setNumero_parcela(5);
            
            Servico s = new Servico();
            s.setId(1);
            p.setServico(s);
            p.setValor(500.0f);
            p.setId(3);
            jdbc.persist(p);
        }else{
            System.out.println("Não conectou!");
        }
    }
}

// FUNCIONARIO OK
// CLIENTE OK
// ORCAMENTO OK
// VEICULO OK
// SERVICO OK
// EQUIPE OK
// MAOOBRA OK
// CURSO OK
// CARGO OK
// PECA OK
// PAGAMENTO OK
