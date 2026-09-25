package br.com.fiap.cineFiap.Service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaService {

    private SalaDAO dao = new SalaDAO();

    public List<Sala> listar() {
        List<Sala> todas = dao.listar();
        List<Sala> ativas = new ArrayList<>();
        for (Sala sala : todas) {
            if (sala.getDataExclusao() == null) {
                ativas.add(sala);
            }
        }
        return ativas;
    }

    public Sala buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public void cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }

        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço da sala deve ser maior que zero.");
        }

        sala.setDataExclusao(null);

        dao.cadastrar(sala);
    }

}