package com.desafio.karyonloja.service;

import com.desafio.karyonloja.exceptions.CompanyNotFoundException;
import com.desafio.karyonloja.exceptions.StoreNotFoundException;
import com.desafio.karyonloja.model.Empresa;
import com.desafio.karyonloja.model.LojaFisica;
import com.desafio.karyonloja.repository.EmpresaRepository;
import com.desafio.karyonloja.repository.LojaFisicaRepository;
import com.desafio.karyonloja.service.dto.EmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    private LojaFisicaRepository lojaFisicaRepository;
    private EmpresaRepository empresaRepository;

    @Autowired
    Service (LojaFisicaRepository lojaFisicaRepository , EmpresaRepository empresaRepository){
        this.lojaFisicaRepository=lojaFisicaRepository;
        this.empresaRepository = empresaRepository;
    }

    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO) throws CompanyNotFoundException {
        Empresa empresa = new Empresa(null,empresaDTO.getCnpj(),empresaDTO.getNomeFantasia(),empresaDTO.getRazaoSocial());
        empresaRepository.save(empresa);

        Integer idEmpresa =  getEmpresa(empresaDTO.getCnpj()).getId();
        List<LojaFisica> lojaFisicas = setIdEmpresa(empresaDTO.getLojaFisicas(),idEmpresa);
        lojaFisicaRepository.saveAll(lojaFisicas);
        EmpresaDTO newEmpresa = getEmpresa(empresa.getId());
        return newEmpresa;
    }

    public List<EmpresaDTO> getEmpresas (){
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaDTO> empresasList = empresas.stream().map(
                e->{
                    List<LojaFisica> lojas = lojaFisicaRepository.findLojaFisicaByIdEmpresa(e.getId());
                    EmpresaDTO empresa = new EmpresaDTO(e,lojas);
                    return empresa;
                }
        ).collect(Collectors.toList());

        return empresasList;
    }

    public EmpresaDTO getEmpresaDTO(Integer id) throws CompanyNotFoundException {
        EmpresaDTO empresaDTO = getEmpresa(id);
        return empresaDTO;

    }

    public EmpresaDTO updateEmpresa(Integer id,Empresa empresa) throws CompanyNotFoundException {
        empresa.setId(id);

        EmpresaDTO empresaDTO = getEmpresa(id);
        empresaDTO.setRazaoSocial(empresa.getRazaoSocial());
        empresaDTO.setNomeFantasia(empresa.getNomeFantasia());

        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setNomeFantasia(empresa.getNomeFantasia());
        empresa.setRazaoSocial(empresa.getRazaoSocial());

        empresaRepository.save(empresa);
        return empresaDTO;

    }


    public EmpresaDTO deleteEmpresa(Integer id) throws CompanyNotFoundException {
        EmpresaDTO empresaDTO = getEmpresa(id);
        Empresa empresa = new Empresa(id,null,null,null);
        //lojaFisicaRepository.deleteByIdEmpresa(id);
        empresaRepository.delete(empresa);
        return empresaDTO;
    }

    public LojaFisica getLojaFisica(Integer id) throws StoreNotFoundException {
        Optional<LojaFisica> lojaFisica = lojaFisicaRepository.findById(id);
        if (lojaFisica.isPresent()){
            return lojaFisica.get();
        }
        throw new StoreNotFoundException("Loja não encontrada");

    }

    public LojaFisica updateLojaFisica (Integer idLoja , LojaFisica lojaFisica) throws StoreNotFoundException {
        LojaFisica loja = getLojaFisica(idLoja);
        lojaFisica.setId(idLoja);
        lojaFisica.setCnpj(loja.getCnpj());
        lojaFisica.setEmpresa(loja.getEmpresa());
        lojaFisicaRepository.save(lojaFisica);
        return lojaFisica;

    }

    public LojaFisica deleteLojaFisica(Integer id) throws StoreNotFoundException {
        LojaFisica lojaFisica = getLojaFisica(id);
        lojaFisicaRepository.delete(lojaFisica);
        return lojaFisica;
    }

    //Necessário existência de empresa para vincular à loja física
    public LojaFisica createLojaFisica(LojaFisica lojaFisica) throws CompanyNotFoundException {
        EmpresaDTO e = getEmpresa(lojaFisica.getEmpresa().getId());
        lojaFisica.setCnpj(e.getCnpj());
        lojaFisicaRepository.save(lojaFisica);
        return lojaFisica;

    }

    private EmpresaDTO getEmpresa(String cnpj){
        Empresa empresa = empresaRepository.findEmpresaByCNPJ(cnpj);
        List<LojaFisica>lojaFisicas = lojaFisicaRepository.findLojaFisicaByIdEmpresa(empresa.getId());
        EmpresaDTO empresaDTO = new EmpresaDTO(empresa,lojaFisicas);
        return empresaDTO;
    }

    private EmpresaDTO getEmpresa(Integer id) throws CompanyNotFoundException {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        List<LojaFisica>lojaFisicas = lojaFisicaRepository.findLojaFisicaByIdEmpresa(id);
        if (empresa.isPresent()){
            Empresa e1 = empresa.get();
            EmpresaDTO empresaDTO = new EmpresaDTO(e1,lojaFisicas);
            return empresaDTO;
        }
        throw new CompanyNotFoundException("Empresa não encontrada");
    }

    private List<LojaFisica> setIdEmpresa(List<LojaFisica> lojasFisicas,Integer idEmpresa){
        List<LojaFisica> lojas = lojasFisicas.stream().map(e->{
            e.getEmpresa().setId(idEmpresa);
            return e;

        }).collect(Collectors.toList());
        return lojas;
    }

    public List<LojaFisica> getListLojaFisica() {
        List<LojaFisica> list = lojaFisicaRepository.findAll();
        return list;
    }
}
