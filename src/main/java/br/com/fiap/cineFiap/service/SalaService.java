package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {

    private final SalaDAO salaDAO;

    public SalaService() {
        this.salaDAO = new SalaDAO();
    }

    public List<Sala> salasAtivas(){
        var salas = salaDAO.listar();
        List<Sala> ativas = new ArrayList<>();

        for (var sala : salas){
            if (sala.getDataExclusao() == null){
                ativas.add(sala);
            }
        }
        return ativas;
    }
    public Sala buscarPorId(Long id){
        var sala = salaDAO.buscarPorId(id);
        return sala;
    }
}