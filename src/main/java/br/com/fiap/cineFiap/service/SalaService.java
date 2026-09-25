package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaService {

    private SalaDAO dao = new SalaDAO();

    public List<Sala> listar() {

        List<Sala> salas = dao.listar();

        List<Sala> salasAtivas = new ArrayList<>();

        for (Sala sala : salas) {

            if (sala.getDataExclusao() == null) {
                salasAtivas.add(sala);
            }
        }

        return salasAtivas;
    }
}