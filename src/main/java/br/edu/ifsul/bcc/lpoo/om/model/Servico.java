
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author telmo
 */
@Entity
@Table(name = "tb_servico")
public class Servico implements Serializable{

    @Id
    @SequenceGenerator(name = "seq_servico", sequenceName = "seq_servicos_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_servico", strategy = GenerationType.SEQUENCE)       
    private Integer id;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)  
    private Calendar data_inicio;
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)  
    private Calendar data_fim;
    
    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;
    
    @OneToMany(mappedBy = "servico")
    private Collection<Pagamento> parcelas;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusServico status;
    
    @ManyToOne
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;

    public Servico() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @return the data_inicio
     */
    public Calendar getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the data_fim
     */
    public Calendar getData_fim() {
        return data_fim;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * @return the equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @param equipe the equipe to set
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
     * @return the parcelas
     */
    public Collection<Pagamento> getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(Collection<Pagamento> parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * @return the status
     */
    public StatusServico getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusServico status) {
        this.status = status;
    }

    /**
     * @return the orcamento
     */
    public Orcamento getOrcamento() {
        return orcamento;
    }

    /**
     * @param orcamento the orcamento to set
     */
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    
    
    
}
