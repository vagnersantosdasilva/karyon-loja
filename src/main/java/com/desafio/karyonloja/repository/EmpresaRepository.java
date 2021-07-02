package com.desafio.karyonloja.repository;

import com.desafio.karyonloja.model.Empresa;
import com.desafio.karyonloja.model.LojaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {

    @Query(nativeQuery = true,value = "select * from empresa where cnpj=?")
    Empresa findEmpresaByCNPJ(String cnpj);
}
