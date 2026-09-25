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
}
