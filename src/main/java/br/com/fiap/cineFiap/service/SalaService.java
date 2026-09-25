package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;

public class SalaService {

    private SalaDAO dao = new SalaDAO();

    public List<Sala> listar() {
        return List.of();
    }

    public Sala buscarPorId(Long id) {

        Sala sala = dao.buscarPorId(id);

        if (sala != null && sala.getDataExclusao() == null) {
            return sala;
        }

        return null;
    }
}