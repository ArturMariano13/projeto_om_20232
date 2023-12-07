
package br.edu.ifsul.bcc.lpoo.om.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author arturmariano
 */
@Entity
@DiscriminatorValue("F")
@Table(name = "tb_funcionario")
public class Funcionario extends Pessoa {
    
    
    @Column(nullable = false, length = 11)
    private String numero_ctps;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)  
    private Calendar data_admmissao;
      
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)    
    private Calendar data_demissao;
    
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    
    @ManyToMany
    @JoinTable(name = "tb_funcionario_curso", joinColumns = {@JoinColumn(name = "funcionario_cpf")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "curso_id")})      
  
    private Collection<Curso> cursos;
    
    @Transient
    private SimpleDateFormat sdf;
    

    public Funcionario() {
        cursos = new ArrayList();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public void setCurso(Curso curso) {
        if(this.cursos == null){
            this.cursos = new ArrayList();
        }
        this.cursos.add(curso);
    }

    public String getNumero_ctps() {
        return numero_ctps;
    }

    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    public Calendar getData_admmissao() {
        return data_admmissao;
    }
    
    public String getData_admmissao_string() {
        if(this.data_admmissao != null){
            return this.data_admmissao.get(Calendar.DAY_OF_MONTH) + "/"+
                   (this.data_admmissao.get(Calendar.MONTH) + 1) + "/"+
                   this.data_admmissao.get(Calendar.YEAR); 
        }else{
            return "";
        }
        
    }
    
    public void setData_admmissao(Calendar data_admmissao) {
        this.data_admmissao = data_admmissao;
    }

    public void setData_admmissao(String data_admmissao){
        
        try{
             this.data_admmissao = Calendar.getInstance();
             this.data_admmissao.setTimeInMillis(sdf.parse(data_admmissao).getTime());
            
        }catch(Exception e){
            
            this.data_admmissao = null;
        }
                
    }

    public Calendar getData_demissao() {
        return data_demissao;
    }

    public void setData_demissao(Calendar data_demissao) {
        this.data_demissao = data_demissao;
    }
    
    public void setData_demissao(String data_demissao) {
        try{
             this.data_demissao = Calendar.getInstance();
             this.data_demissao.setTimeInMillis(sdf.parse(data_demissao).getTime());
            
        }catch(Exception e){
            
            this.data_demissao = null;
        }
       
    }
    
    @Override
    public String toString(){
        return this.getCpf();
    }  
}
