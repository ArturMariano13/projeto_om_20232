
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */
@Entity
@Table(name = "tb_equipe")
public class Equipe implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_equipe", sequenceName = "seq_equipe_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_equipe", strategy = GenerationType.SEQUENCE)       
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = true, length = 100)
    private String especialidades;
    
    @ManyToMany
    @JoinTable(name = "tb_equipe_funcionario", joinColumns = {@JoinColumn(name = "equipe_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "funcionario_cpf")})   
 
    private Collection<Funcionario> funcionarios;

    public Equipe() {
        funcionarios = new ArrayList();
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the especialidades
     */
    public String getEspecialidades() {
        return especialidades;
    }

    /**
     * @param especialidades the especialidades to set
     */
    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public Collection<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Collection<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
}
