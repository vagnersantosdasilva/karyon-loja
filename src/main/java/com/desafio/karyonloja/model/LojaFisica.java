package com.desafio.karyonloja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Where(clause = "DELETED = 0")
public class LojaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_loja_fisica")
    private Integer id;

    @Length(max=14,message = "CNPJ com tamanho  inválido")
    private String cnpj;

    @Column(name="nome_loja" ,nullable = false )
    private String loja;

    private String enderecoCompleto;

    @Column(name="nome_contato")
    private String contato;

    @Length(max=11,message = "Tamanho  inválido")
    @Column(name="tel_fixo")
    private String tel;

    @Length(max=11,message = "Tamanho  inválido")
    @Column(name="celular")
    private String cel;

    @Length(max=11,message = "Tamanho  inválido")
    private String whatsapp;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="id_empresa" )
    protected Empresa empresa;

    @JsonIgnore
    @Column(name="DELETED")
    private Integer deleted = 0;

    public LojaFisica(String cnpj, String nomeLoja, String enderecoCompleto, String contato, String tel, String cel, String whatsapp, Empresa empresa) {
        this.cnpj = cnpj;
        this.loja = nomeLoja;
        this.enderecoCompleto = enderecoCompleto;
        this.contato = contato;
        this.tel = tel;
        this.cel = cel;
        this.whatsapp = whatsapp;
        this.empresa = empresa;
    }

    public LojaFisica() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idLojaFisica) {
        this.id = idLojaFisica;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String nomeLoja) {
        this.loja = nomeLoja;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String nomeContato) {
        this.contato = nomeContato;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String telFixo) {
        this.tel = telFixo;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String celular) {
        this.cel = celular;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
