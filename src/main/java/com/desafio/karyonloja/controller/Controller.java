package com.desafio.karyonloja.controller;

import com.desafio.karyonloja.exceptions.CompanyNotFoundException;
import com.desafio.karyonloja.exceptions.StoreNotFoundException;
import com.desafio.karyonloja.model.Empresa;
import com.desafio.karyonloja.model.LojaFisica;
import com.desafio.karyonloja.service.Service;
import com.desafio.karyonloja.service.dto.EmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/empresa/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresa(@PathVariable Integer id) throws CompanyNotFoundException {

        EmpresaDTO empresaDTO = service.getEmpresaDTO(id);
        ResponseEntity responseEntity = new ResponseEntity(empresaDTO,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/empresa")
    public ResponseEntity<?> getEmpresas(){
        List<EmpresaDTO>empresas = service.getEmpresas();
        ResponseEntity<?> responseEntity = new ResponseEntity(empresas,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/empresa")
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresa) throws CompanyNotFoundException {
        EmpresaDTO empresaDTO  = service.createEmpresa(empresa);
        ResponseEntity<EmpresaDTO> responseEntity = new ResponseEntity<>(empresaDTO,HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<?> updateEmpresa(@PathVariable Integer id,@RequestBody Empresa empresa) throws CompanyNotFoundException {
        EmpresaDTO empresaDTO = service.updateEmpresa(id,empresa);
        ResponseEntity responseEntity = new ResponseEntity(empresaDTO,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("empresa/{id}")
    public ResponseEntity<?> deleteEmpresa (@PathVariable Integer id) throws CompanyNotFoundException {
        service.deleteEmpresa(id);
        ResponseEntity responseEntity = new ResponseEntity(null,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/lojafisica/{id}")
    public ResponseEntity<?>getLojaFisica(@PathVariable Integer id) throws StoreNotFoundException {
        LojaFisica lojaFisica =service.getLojaFisica(id);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(lojaFisica,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/lojafisica/{id}")
    public ResponseEntity<?> deleteLojaFisica(@PathVariable Integer id) throws StoreNotFoundException {
        LojaFisica lojaFisica = service.deleteLojaFisica(id);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(lojaFisica,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/lojafisica/{id}")
    public ResponseEntity<?> updateLojaFisica(@PathVariable Integer id,@RequestBody LojaFisica lojaFisica) throws StoreNotFoundException {
        LojaFisica loja = service.updateLojaFisica(id,lojaFisica);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(loja,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/lojafisica")
    public ResponseEntity<?> createLojaFisica(@RequestBody LojaFisica lojaFisica){
        LojaFisica loja = service.createLojaFisica(lojaFisica);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(loja,HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/lojafisica")
    public ResponseEntity<?> getListLojaFisica(){
        List<LojaFisica>list  = service.getListLojaFisica();
        ResponseEntity<List<LojaFisica>> responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;
    }

}
