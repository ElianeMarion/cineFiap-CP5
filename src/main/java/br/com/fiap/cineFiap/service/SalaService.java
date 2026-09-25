package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;
import java.util.stream.Collectors;

public class SalaService {

    private final SalaDAO dao = new SalaDAO();

    public List<Sala> listar() {
        return dao.listar().stream()
                .filter(sala -> sala.getDataExclusao() == null)
                .collect(Collectors.toList());
    }

    public Sala buscarPorId(Long id) {
        Sala sala = dao.buscarPorId(id);

        if (sala.getId() == null) {
            return null; // não existe
        }
        if (sala.getDataExclusao() != null) {
            return null; // excluída logicamente
        }
        return sala;
    }
    public void cadastrar(Sala sala) {
        if (sala.getNome() == null || sala.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        }
        if (sala.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço da sala deve ser maior que zero.");
        }
        if (sala.getDataExclusao() != null) {
            throw new IllegalArgumentException("Uma sala cadastrada não pode possuir data de exclusão.");
        }
        dao.cadastrar(sala);
    }
    public void alterar(Long id, Sala sala) {
        Sala existente = dao.buscarPorId(id);

        if (existente.getId() == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }
        if (existente.getDataExclusao() != null) {
            throw new IllegalArgumentException("Sala inativa não pode ser alterada.");
        }
        if (sala.getId() == null || !id.equals(sala.getId())) {
            throw new IllegalArgumentException("O ID da URL deve corresponder ao ID do objeto.");
        }
        dao.alterar(sala);
    }
}