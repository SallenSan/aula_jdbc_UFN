package aula.src.main.java.org.example.dao;

import aula.src.main.java.org.example.model.Pessoa;

import java.util.List;

public interface PessoaDao {
    public int addPessoa(Pessoa p);
    public int updatePessoa(Pessoa p);
    public int deletePessoa(int id);
    public Pessoa getPessoa(int id);
    public List<Pessoa> getAllPessoas();
}
