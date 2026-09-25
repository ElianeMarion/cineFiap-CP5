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

    public Sala buscarPorId(Long id) {
        Sala sala = salaDAO.buscarPorId(id);
        if (sala.getId() == null || sala.getDataExclusao() != null) {
            return null;
        }
        return sala;
    }

    public Sala cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }
        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço da sala deve ser maior que zero.");
        }
        if (sala.getDataExclusao() != null) {
            throw new IllegalArgumentException("Uma sala nova não pode possuir data de exclusão.");
        }
        salaDAO.cadastrar(sala);
        return sala;
    }


    public Sala alterar(Long id, Sala sala) {
        if (!id.equals(sala.getId())) {
            throw new IllegalArgumentException("O ID da URL não corresponde ao ID do objeto.");
        }
        Sala salaExistente = salaDAO.buscarPorId(id);
        if (salaExistente.getId() == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }
        if (salaExistente.getDataExclusao() != null) {
            throw new IllegalArgumentException("Sala inativa não pode ser alterada.");
        }
        salaDAO.alterar(sala);
        return sala;
    }

}