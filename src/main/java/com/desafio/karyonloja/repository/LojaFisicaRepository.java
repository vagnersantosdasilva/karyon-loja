package com.desafio.karyonloja.repository;

import com.desafio.karyonloja.model.LojaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaFisicaRepository extends JpaRepository<LojaFisica,Integer> {

    @Query(nativeQuery = true,value = "select * from loja_fisica where id_empresa=?")
    List<LojaFisica> findLojaFisicaByIdEmpresa(Integer id);

    @Query(nativeQuery = true,value = "delete from loja_fisica where id_empresa=?")
    void deleteByIdEmpresa (Integer idEmpresa);

}
