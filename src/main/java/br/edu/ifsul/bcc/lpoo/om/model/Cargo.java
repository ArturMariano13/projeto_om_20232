
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */
@Entity
@Table(name = "tb_cargo")
@NamedQueries({@NamedQuery(name="Cargo.orderbyid", query="select c from Cargo c "
                                                        + "order by c.id asc")})
public class Cargo implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cargo", strategy = GenerationType.SEQUENCE)   
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String descricao;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cargo() {
        
    }

    public Cargo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    
    
    
}
