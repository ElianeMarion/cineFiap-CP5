package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;
import java.util.Objects;

public class SalaService {
    private final SalaDAO salaDAO;

    public SalaService(){
        this.salaDAO = new SalaDAO();
    }
    public List<Sala> listar(){
        if(salaDAO.listar() != null){
            return salaDAO.listar();
        }
        throw new IllegalArgumentException("Nenhuma sala");
    }

    public Sala buscarPorId(Long id){
        var sala = salaDAO.buscarPorId(id);
        if(sala.getId() != null){
            return sala;
        }
        throw new IllegalArgumentException("Nenhuma sala encontrada");
    }

    public void cadastrar(Sala sala){
        if(sala.getPreco() < 0){
            throw new RuntimeException("Dados incompletos");
        } else if (sala.getNome() == null) {
            throw new RuntimeException("Dados incompletos");
        } else if (sala.getDataExclusao() != null) {
            throw new RuntimeException("Não foi possível cadastrar");
        }else{
            salaDAO.cadastrar(sala);
        }
    }

    public void alterar(Long id, Sala sala){
        if(!Objects.equals(id, sala.getId())){
            throw new IllegalArgumentException("Erro ao consultar ID");
        }
        Sala salaExistente = buscarPorId(id);
        if(salaExistente == null){
            throw new IllegalArgumentException("Sala não existente");
        }
        if(salaExistente.getDataExclusao() != null){
            throw new IllegalArgumentException("Sala inativa");
        }
        salaDAO.alterar(sala);
    }
}
