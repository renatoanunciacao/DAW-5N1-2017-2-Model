/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Renato
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario extends PessoaFisica implements Serializable {
    @NotNull(message = "O apelido não pode ser nullo")
    @NotBlank(message = "O apelido não pode estar em branco")
    @Length(max = 20, message = "O apelido não pode ter mais do que {max} caracteres")
    @Column(name = "apelido", length = 20, nullable = false)
    private String apelido;
    @NotNull(message = "A senha  não pode ser me branca")
    @NotBlank(message = "A senha não pode ser em branca")
    @Length(max = 20, message = "A senha não pode ter mais do que {max} caracteres")
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    @Column(name = "administrador", nullable = false)
    private boolean administador;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AcessoUsuario> acessos = new ArrayList<>();
    
    
   
    public Usuario(){
        
    }

    public void adicionarAcesso(AcessoUsuario obj){
        obj.setUsuario(this);
        this.acessos.add(obj);
    }
    public void removerAcesso(int index){
        this.acessos.remove(index);
    }
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAdministador() {
        return administador;
    }

    public void setAdministador(boolean administador) {
        this.administador = administador;
    }

    public List<AcessoUsuario> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<AcessoUsuario> acessos) {
        this.acessos = acessos;
    }
    
    
}
