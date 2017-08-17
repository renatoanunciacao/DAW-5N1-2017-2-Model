
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "acesso_usuario")
public class AcessoUsuario implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_acesso_usuario", sequenceName = "seq_acesso_usuario_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_acesso_usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name = "data", nullable = false)
    private Calendar data;
    @NotNull(message = "O ip não pode ser nulo")
    @NotBlank(message = "O ip não pode ser em branco")
    @Length(max = 15, message = "O ip não pode ser maior do que {max} caracteres")
    @Column(name = "ipAcesso", length = 15, nullable = false)
    private String ipAcesso;
    @NotNull(message = "O usuário não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_usuario_id")
    private Usuario usuario;

    
    
    public AcessoUsuario(){
        
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getIpAcesso() {
        return ipAcesso;
    }

    public void setIpAcesso(String ipAcesso) {
        this.ipAcesso = ipAcesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AcessoUsuario other = (AcessoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
