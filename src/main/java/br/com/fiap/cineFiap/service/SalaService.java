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


}