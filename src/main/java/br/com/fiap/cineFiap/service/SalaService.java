package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

public class SalaService {

    private SalaDAO dao = new SalaDAO();

    public Sala buscarPorId(Long id) {

        Sala sala = dao.buscarPorId(id);

        if (sala == null) {
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
            throw new IllegalArgumentException("O preço da sala deve ser maior que zero.");
        }

        sala.setDataExclusao(null);

        return dao.cadastrar(sala);
    }

    public void alterar(Long id, Sala sala) {

        Sala salaExistente = dao.buscarPorId(id);

        if (salaExistente == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }

        if (salaExistente.getDataExclusao() != null) {
            throw new IllegalArgumentException("A sala está excluída.");
        }

        if (!id.equals(sala.getId())) {
            throw new IllegalArgumentException(
                    "O ID da URL deve ser igual ao ID da sala."
            );
        }

        dao.alterar(sala);
    }
}