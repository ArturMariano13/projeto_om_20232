
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 *  ALTERAÇÃO DE STRING PARA INTEGER O ID --> NÃO FUNCIONA A SEQUÊNCIA COM STRING 
 */
@Entity
@Table(name = "tb_maoobra")
public class MaoObra implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_maoobra", sequenceName = "seq_maoobra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_maoobra", strategy = GenerationType.SEQUENCE)       
    private String id;
    
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private Date tempo_estimado_execucao;
    
    @Column(nullable = false, precision = 2)
    private Float valor;

    public MaoObra() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getTempo_estimado_execucao() {
        return tempo_estimado_execucao;
    }

    public void setTempo_estimado_execucao(Date tempo_estimado_execucao) {
        this.tempo_estimado_execucao = tempo_estimado_execucao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    
    
}
