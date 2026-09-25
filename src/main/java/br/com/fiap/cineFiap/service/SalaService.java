package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaDAO salaDAO;

    public List<Sala> listar() {
        return salaDAO.listar().stream()
                .filter(sala -> sala.getDataExclusao() == null)
                .toList();
    }
}