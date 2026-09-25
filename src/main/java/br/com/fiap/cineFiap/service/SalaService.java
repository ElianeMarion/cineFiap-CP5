package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class SalaService {
    private final SalaDAO salaDAO;

    public SalaService() {
        this.salaDAO = new SalaDAO();
    }

    public List<Sala> listar() {
        return salaDAO.listar();
    }

    public Sala buscarPorId(Long id) {
        return salaDAO.buscarPorId(id);
    }

    public Sala cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }
        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        sala.setDataExclusao(null);

        salaDAO.cadastrar(sala);
        return sala;
    }

    public Sala alterar(Long id, Sala sala) {
        Sala existente = salaDAO.buscarPorId(id);

        if (existente.getId() == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }
        if (existente.getDataExclusao() != null) {
            throw new IllegalArgumentException("Sala inativa não pode ser alterada.");
        }
        if (!id.equals(sala.getId())) {
            throw new IllegalArgumentException("O ID da URL não corresponde ao ID do objeto.");
        }

        salaDAO.alterar(sala);
        return sala;
    }

    public Sala excluir(Long id) {
        Sala existente = salaDAO.buscarPorId(id);
        if (existente.getId() == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }
        if (existente.getDataExclusao() != null) {
            throw new IllegalArgumentException("Sala já está inativa.");
        }
        salaDAO.deletar(id);
        return existente;
    }
}

