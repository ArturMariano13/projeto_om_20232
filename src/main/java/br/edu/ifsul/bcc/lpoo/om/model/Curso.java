
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
@Table(name = "tb_curso")
public class Curso implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)   
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;

    public Curso() {
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
    
    
}
