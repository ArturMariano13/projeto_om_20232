
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author arturmariano
 */
@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Pessoa implements Serializable {
    
    @Id
    private String cpf;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 6)
    private String senha;
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)    
    private Calendar data_nascimento;
    
    @Column(nullable = true, length = 8)
    private String cep;
    
    @Column(nullable = true, length = 100)
    private String numero;
    
    @Column(nullable = true, length = 100)
    private String complemento;
    
    //transient significa que não será persistido pelo jpa
    @Transient
    private String tipo;
    
    @Transient
    private SimpleDateFormat sdf;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Pessoa() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the data_nascimento
     */
    public Calendar getData_nascimento() {
        return data_nascimento;
    }
    
    public String getData_nascimento_string() {
        if(this.data_nascimento != null){
            return this.data_nascimento.get(Calendar.DAY_OF_MONTH) + "/"+
                   (this.data_nascimento.get(Calendar.MONTH) + 1) + "/"+
                   this.data_nascimento.get(Calendar.YEAR); 
        }else{
            return "";
        }
        
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
    public void setData_nascimento(String data_nascimento){
        
        try{
             this.data_nascimento = Calendar.getInstance();
             this.data_nascimento.setTimeInMillis(sdf.parse(data_nascimento).getTime());
            
        }catch(Exception e){
            
            this.data_nascimento = null;
        }
                
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
