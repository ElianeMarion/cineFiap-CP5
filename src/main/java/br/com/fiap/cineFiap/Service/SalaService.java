package br.com.fiap.cineFiap.service;

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
}