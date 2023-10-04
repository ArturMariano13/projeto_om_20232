
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_pagamento", sequenceName = "seq_pagamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pagamento", strategy = GenerationType.SEQUENCE)   
    private Integer id;
    
    @Column(nullable = false)    
    private Integer numero_parcela;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)  
    private Calendar data_vencimento;
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)  
    private Calendar data_pagamento;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "servico_id")
    private Servico servico;
    
    public Pagamento() {
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
     * @return the numero_parcela
     */
    public Integer getNumero_parcela() {
        return numero_parcela;
    }

    /**
     * @param numero_parcela the numero_parcela to set
     */
    public void setNumero_parcela(Integer numero_parcela) {
        this.numero_parcela = numero_parcela;
    }

    /**
     * @return the data_vencimento
     */
    public Calendar getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(Calendar data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the data_pagamento
     */
    public Calendar getData_pagamento() {
        return data_pagamento;
    }

    /**
     * @param data_pagamento the data_pagamento to set
     */
    public void setData_pagamento(Calendar data_pagamento) {
        this.data_pagamento = data_pagamento;
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
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    
    
}
