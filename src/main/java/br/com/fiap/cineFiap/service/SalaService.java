package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaDAO salaDAO = new SalaDAO();

    public List<Sala> listar() {
        return salaDAO.listar();
    }

    public Sala buscarPorId(Long id) {
        Sala sala = salaDAO.buscarPorId(id);

        if (sala == null || sala.getId() == null) {
            return null; // não encontrada
        }

        if (sala.getDataExclusao() != null) {
            return null; // está excluída logicamente
        }

        return sala;
    }
}