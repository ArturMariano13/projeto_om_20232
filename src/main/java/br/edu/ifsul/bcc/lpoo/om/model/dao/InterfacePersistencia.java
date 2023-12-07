
package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import java.util.Collection;

/**
 *
 * @author arturmariano
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
    
    public Funcionario doLogin(String cpf, String senha) throws Exception;
    public Collection<Servico> listServico() throws Exception;
    
    public Collection<MaoObra> listMaoObras(String filtro_descricao) throws Exception;
}
