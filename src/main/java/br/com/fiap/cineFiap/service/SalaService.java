package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    private final SalaDAO salaDAO;

    public SalaService() {
        this.salaDAO = new SalaDAO();
    }

    public List<Sala> listar(){
        return salaDAO.listar();
    }

    public Sala buscarPorId(Long id){
        var sala = salaDAO.buscarPorId(id);
        if (sala.getDataExclusao() == null)
            return sala;
        return null;
    }

    public void inserirSala(Sala sala){
        try {
            if (sala.getNome() == null){
                throw new IllegalArgumentException("O nome da sala é obrigatório");
            } else if (sala.getPreco() <= 0) {
                throw new IllegalArgumentException("O valor deve ser maior que 0");
            } else if (sala.getDataExclusao() != null) {
                throw new IllegalArgumentException("Uma sala sendo cadastrada não pode possuir data de exclusão");
            }
            salaDAO.cadastrar(sala);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }


}
