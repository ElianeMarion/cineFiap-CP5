package br.com.fiap.cineFiap.service;


import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.exceptions.FilmeNaoExisteException;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;

public class SalaService {

    private final SalaDAO saladao;


    public SalaService(SalaDAO saladao) {
        this.saladao = saladao;
    }



    public List<Sala> listar() {
        var salas = saladao.listar();
        if (salas.isEmpty())
            throw new FilmeNaoExisteException("Nao existe nenhum livro cadastrado");

        return salas;
    }


    public Sala buscarPorId(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id nao pode ser nulo");

        var lista = saladao.buscarPorId(id);
        if (lista.getId() == 0)
            throw new FilmeNaoExisteException("filme nao achou");
        return lista;
    }

    public void cadastrar(Sala sala) {
        if (sala == null)
            throw new IllegalArgumentException("nao pode ser nulo");
        if (sala.getNome() == null)
            throw new IllegalArgumentException("nome nao pode ser nulo");

        saladao.cadastrar(sala);
    }
}
