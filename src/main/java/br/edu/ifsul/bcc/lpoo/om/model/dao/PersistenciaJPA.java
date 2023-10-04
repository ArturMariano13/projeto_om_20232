

package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.util.Collection;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author telmo
 * 
 */
public class PersistenciaJPA implements InterfacePersistencia{
    
    public EntityManagerFactory factory;    //fabrica de gerenciadores de entidades
    public EntityManager entity;            //gerenciador de entidades JPA


    public PersistenciaJPA() {
        
        //parametro: é o nome da unidade de persistencia (Persistence Unit)
        factory = Persistence.createEntityManagerFactory("pu_db_om_lpoo_20232");
        entity = factory.createEntityManager(); 
        
    }

    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen();   
    }

    @Override
    public void fecharConexao() {
        
        entity.close(); 
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        return entity.find(c, id);//encontra um determinado registro    
    }

    @Override
    public void persist(Object o) throws Exception {
        
        entity.getTransaction().begin();// abrir a transacao.
        entity.persist(o); //realiza o insert ou update.
        entity.getTransaction().commit(); //comita a transacao (comando sql)         
        
    }

    @Override
    public void remover(Object o) throws Exception {
        entity.getTransaction().begin();// abrir a transacao.
        entity.remove(o); //realiza o delete
        entity.getTransaction().commit(); //comita a transacao (comando sql)    
    }

    @Override
    public Collection<Cargo> listCargos() throws Exception {
        
         return entity.createNamedQuery("Cargo.orderbyid").getResultList();
        
    }

    @Override
    public Collection<Funcionario> listFuncionarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Cliente> listClientes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
