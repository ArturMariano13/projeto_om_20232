
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */

@Entity
@Table(name = "tb_peca")
public class Peca implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_peca", sequenceName = "seq_peca_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_peca", strategy = GenerationType.SEQUENCE)              
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    @Column(nullable = true, length = 200)
    private String fornecedor;

    public Peca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
}
