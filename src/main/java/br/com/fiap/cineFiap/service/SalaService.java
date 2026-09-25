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

    public void cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }

        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        sala.setDataExclusao(null);

        salaDAO.cadastrar(sala);
    }

    public void alterar(Long id, Sala sala) {
        Sala existente = salaDAO.buscarPorId(id);

        if (existente == null || existente.getId() == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }

        if (existente.getDataExclusao() != null) {
            throw new IllegalArgumentException("Não é possível alterar uma sala excluída.");
        }

        if (!id.equals(sala.getId())) {
            throw new IllegalArgumentException("O ID da URL não corresponde ao ID do objeto.");
        }

        salaDAO.alterar(sala);
    }
}