package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;

public class SalaService {
    private final SalaDAO salaDAO;

    public SalaService(){
        this.salaDAO = new SalaDAO();
    }
    public List<Sala> listar(){
        var sala = salaDAO.listar();
        if(sala != null){
            return sala;
        }
        throw new IllegalArgumentException("Nenhuma sala encontrada");
    }
}
