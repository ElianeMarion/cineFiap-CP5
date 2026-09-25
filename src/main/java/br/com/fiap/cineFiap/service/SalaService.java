package br.com.fiap.cineFiap.service;


import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.exceptions.FilmeNaoExisteException;
import br.com.fiap.cineFiap.models.Sala;

import java.time.LocalDateTime;
import java.util.List;

public class SalaService {

    private final SalaDAO saladao;


    public SalaService() {
        this.saladao = new SalaDAO();
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
        if (lista.getId() == null)
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

    public void alterar(Sala sala) {
        if (sala == null || sala.getId() == null)
            throw new IllegalArgumentException("nao pode ser nulo");

        var existe = saladao.buscarPorId(sala.getId());
        if (existe.getId() != null) {
            saladao.alterar(sala);
        } else {
            throw new FilmeNaoExisteException("pai, nao acho nada");
        }
    }

    public void excluir(Long id) {

        var sala = saladao.buscarPorId(id);

        if (sala == null || sala.getId() == 0)
            throw new IllegalArgumentException("nao existe");

        if (sala.getDataExclusao() != null) {
            throw new IllegalArgumentException("Esta sala já está inativada!");
        }

        sala.setDataExclusao(LocalDateTime.now());

        saladao.alterar(sala);

    }
}
