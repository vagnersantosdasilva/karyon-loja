package com.desafio.karyonloja.service.dto;

import com.desafio.karyonloja.model.Empresa;
import com.desafio.karyonloja.model.LojaFisica;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO {

    private Integer id;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private List<LojaFisica> lojaFisicas = new ArrayList();

    public EmpresaDTO(Integer id, String cnpj, String nomeFantasia, String razaoSocial, List<LojaFisica> lojaFisicas) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.lojaFisicas = lojaFisicas;
    }

    public EmpresaDTO (Empresa empresa , List<LojaFisica>lojaFisicas){
        this.id = empresa.getId();
        this.cnpj = empresa.getCnpj();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.razaoSocial = empresa.getRazaoSocial();
        this.lojaFisicas = lojaFisicas;
    }

    public EmpresaDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<LojaFisica> getLojaFisicas() {
        return lojaFisicas;
    }

    public void setLojaFisicas(List<LojaFisica> lojaFisicas) {
        this.lojaFisicas = lojaFisicas;
    }

    public void addLojaFisicas (LojaFisica lojaFisica){
        this.lojaFisicas.add(lojaFisica);
    }
}
