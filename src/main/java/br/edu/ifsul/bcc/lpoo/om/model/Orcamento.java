
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author telmo
 */
@Entity
@Table(name = "tb_orcamento")
public class Orcamento implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_orcamento", sequenceName = "seq_orcamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_orcamento", strategy = GenerationType.SEQUENCE)           
    private Integer id;
    
    @Column(nullable = true, length = 100)
    private String observacoes;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @ManyToOne
    @JoinColumn(name = "cliente_cpf", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_cpf", nullable = false)
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;
   
    @ManyToMany
    @JoinTable(name = "tb_orcamento_peca", joinColumns = {@JoinColumn(name = "orcamento_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "peca_id")})        
    private Collection<Peca> pecas;
    
    @ManyToMany
    @JoinTable(name = "tb_orcamento_maoobra", joinColumns = {@JoinColumn(name = "orcamento_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "maoobra_id")})      
    private Collection<MaoObra> maoobras;

    public Orcamento() {
        maoobras = new ArrayList();
        pecas = new ArrayList();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Collection<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(Collection<Peca> pecas) {
        this.pecas = pecas;
    }

    public Collection<MaoObra> getMaoobras() {
        return maoobras;
    }

    public void setMaoobras(Collection<MaoObra> maoobras) {
        this.maoobras = maoobras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    
    
}
