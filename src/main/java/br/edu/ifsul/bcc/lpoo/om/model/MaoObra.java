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
 * @author arturmariano
 */
@Entity
@Table(name = "tb_maoobra")
public class MaoObra implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_maoobra", sequenceName = "seq_maoobra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_maobra", strategy = GenerationType.SEQUENCE)       
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private Date tempo_estimado_execucao;
    
    @Column(nullable = false, precision = 2)
    private Float valor;

    public MaoObra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    
    @Override
    public String toString(){
        return this.getId().toString();
    }
    
    
}