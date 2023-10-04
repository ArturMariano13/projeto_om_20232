
package br.edu.ifsul.bcc.lpoo.om.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue(value = "C")
public class Cliente extends Pessoa{
    
    @Column(nullable = true, length = 200)
    private String observacoes;
    
    
    @ManyToMany
    @JoinTable(name = "tb_cliente_veiculo", joinColumns = {@JoinColumn(name = "cliente_cpf")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "veiculo_id")})      
    private Collection<Veiculo> veiculo;

    public Cliente() {
        veiculo = new ArrayList();
        observacoes = " ";
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Collection<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Collection<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }
    
    
    
}
