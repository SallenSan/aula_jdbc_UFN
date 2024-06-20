package org.example;

import aula.src.main.java.org.example.dao.PessoaDaoImplementacao;
import aula.src.main.java.org.example.model.Pessoa;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PessoaDaoImplementacao pessoaDao = new PessoaDaoImplementacao();
        List<Pessoa> pessoas;

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Joao");
        pessoa.setEmail("joao@mail.com");
        pessoaDao.addPessoa(pessoa);




    }
}