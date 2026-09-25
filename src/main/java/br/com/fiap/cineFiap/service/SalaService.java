package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void cadastrar(Sala sala){
        if (sala.getNome() == null || sala.getNome().isBlank()){
            System.out.println("ERRO: O nome da sala é obrigatório.");
            throw new IllegalArgumentException(
                    "O nome da sala é obrigatório.");
        }
        if (sala.getPreco() <= 0){
            System.out.println("ERRO: O preço deve ser maior que zero.");
            throw new IllegalArgumentException(
                    "O preço deve ser maior que zero.");
        }
        if (sala.getDataExclusao() != null){
            System.out.println("ERRO: Uma sala nova não pode possuir data de exclusão.");
            throw new IllegalArgumentException(
                    "Uma sala nova não pode possuir data de exclusão.");
        }
        salaDAO.cadastrar(sala);
    }
    public void alterar(Long id, Sala sala){
        var salaExistente = salaDAO.buscarPorId(id);

        if (salaExistente.getId() == null){
            System.out.println("ERRO: Sala não encontrada.");
            throw new IllegalArgumentException("Sala não encontrada");
        }
        if (salaExistente.getDataExclusao() != null){
            System.out.println("ERRO: A sala está inativa.");
            throw new IllegalArgumentException("A sala está inativa");
        }
        if (!Objects.equals(salaExistente.getId(), sala.getId())){
            System.out.println("ERRO: O id informado não corresponde à sala.");
            throw new IllegalArgumentException("O id informado não corresponde ao da sala");
        }
        salaDAO.alterar(sala);
    }
    public void excluir(Long id){
        var sala = salaDAO.buscarPorId(id);

        if (sala.getId() == null){
            System.out.println("ERRO: Sala não encontrada.");
            throw new IllegalArgumentException("Sala não encontrada");
        }
        if (sala.getDataExclusao() != null){
            System.out.println("ERRO: A sala já está inativa.");
            throw new IllegalArgumentException("A sala já está inativa");
        }
        salaDAO.deletar(id);
    }
}