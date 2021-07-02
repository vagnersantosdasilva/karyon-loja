package com.desafio.karyonloja.tests;

import com.desafio.karyonloja.model.Empresa;
import com.desafio.karyonloja.model.LojaFisica;
import com.desafio.karyonloja.repository.EmpresaRepository;
import com.desafio.karyonloja.repository.LojaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InsertTestData {

    private LojaFisicaRepository lojaFisicaRepository;
    private EmpresaRepository empresaRepository;

    @Autowired
    public InsertTestData(EmpresaRepository empresaRepository , LojaFisicaRepository lojaFisicaRepository){
        this.lojaFisicaRepository = lojaFisicaRepository;
        this.empresaRepository =empresaRepository;
    }

    @EventListener
    public void onApplicationEnvent(ContextRefreshedEvent event){


        Empresa e1 = new Empresa(1,"12345678910123","Nome fantasia1","Razão Social1");
        Empresa e2 = new Empresa(2,"12345678911123","Nome fantasia2","Razão Social2");
        Empresa e3 = new Empresa(3,"12345678913123","Nome fantasia3","Razão Social3");

        LojaFisica lj1 = new LojaFisica("12345678910123","xyz","Rua w , 223, Avenida X, Rio de Janeiro, RJ.",
                "Fulano de Tal","2122223333","21999999999","21999999999",e1
                );

        LojaFisica lj2 = new LojaFisica("12345678910123","xyz","Rua z , 111, Avenida A, Rio de Janeiro, RJ.",
                "Fulano de Tal","2122223333","21999999999","21999999999",e1
        );

        LojaFisica lj3 = new LojaFisica("12345678913123","wzt","Rua w , 223, Avenida B, Rio de Janeiro, RJ.",
                "Beltrano de Tal","2122225555","21999999955","21999999955",e3
        );

        empresaRepository.saveAll(Arrays.asList(e1,e2,e3));
        lojaFisicaRepository.saveAll(Arrays.asList(lj1,lj2,lj3));
    }
}
