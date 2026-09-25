package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalaService{
    private final SalaDAO salaDAO;

    public SalaService(){
        this.salaDAO = new SalaDAO();
    }

    public List<Sala> listar(){
        System.out.println("Teste se entra na Service");
        return salaDAO.listar();
    }

    public Sala listarPorId(Long id){

        var sala = salaDAO.buscarPorId(id);
        if (sala.getDataExclusao() != null){
            throw new IllegalArgumentException(
                    "Id procurado foi excluido logicamente");
        }
        return sala;
    }

    public void cadastrar (Sala sala){
        if(sala.getNome() == null || sala.getNome() == ""){
            throw new IllegalArgumentException("O nome da sala é obrigatório.");
        };
        if(sala.getPreco() <= 0) {
            throw new IllegalArgumentException(
                    "Inserção de preço acima de 0 é obrigatória.");
        }
        if(sala.getDataExclusao() != null){
            throw new IllegalArgumentException("Não é possivel a inserção de data de exclusão.");
        }

        salaDAO.cadastrar(sala);
    }
    public void alterar(Sala sala){

        if(sala == null){
            throw new IllegalArgumentException("Sala não existe.");
        }
        if(sala.getDataExclusao() != null){
            throw new IllegalArgumentException("A sala tem que estar ativa.");
        }

        salaDAO.alterar(sala);
    }

    public void deletar(long id) {
        salaDAO.deletar(id);
    }
}



