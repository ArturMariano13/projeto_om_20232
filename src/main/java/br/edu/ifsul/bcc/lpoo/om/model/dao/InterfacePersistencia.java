
package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.util.Collection;

/**
 *
 * @author telmo
 */
public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;
    
    public Collection<Cargo> listCargos() throws Exception;
    public Collection<Funcionario> listFuncionarios() throws Exception;
    public Collection<Cliente> listClientes() throws Exception;
    
}
