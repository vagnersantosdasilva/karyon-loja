package com.desafio.karyonloja.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
@Entity
@Where(clause = "DELETED = 0")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Integer id;

    @Column(nullable = false,unique = true ,length = 14)
    @Length(min=14, max=14,message = "CNPJ com tamanho  inválido")
    private String cnpj;


    @Length(min=1,max=20,message = "NomeFantasia com tamanho inválido")
    @NotEmpty(message = "O nome fantasia é obrigatório")
    @Column(nullable = false,unique = true )
    private String nomeFantasia;
    private String razaoSocial;

    @JsonIgnore
    @Column(name="DELETED")
    private Integer deleted = 0;


    public Empresa (Integer id, String cnpj , String nomeFantasia, String razaoSocial ){
        this.id = id;
        this.cnpj=cnpj;
        this.nomeFantasia=nomeFantasia;
        this.razaoSocial=razaoSocial;

    }

    public Empresa() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idEmpresa) {
        this.id = idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
