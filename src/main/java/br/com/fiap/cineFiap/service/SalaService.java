package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaDAO dao = new SalaDAO();

    public List<Sala> listar() {
        return dao.listar();
    }

    public Sala buscarPorId(Long id) {
        Sala sala = dao.buscarPorId(id);

        if (sala.getId() == null) {
            return null;
        }

        if (sala.getDataExclusao() != null) {
            return null;
        }

        return sala;
    }

    public Sala cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }

        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        sala.setDataExclusao(null);

        dao.cadastrar(sala);
        return sala;
    }
}

