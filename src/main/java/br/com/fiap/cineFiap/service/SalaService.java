package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SalaService {
    private SalaDAO salaDAO = new SalaDAO();

    public List<Sala> listar(){return salaDAO.listar();}

    public Sala buscaPorId(long id){return salaDAO.buscarPorId(id);}

    public void cadastrar(Sala sala){
        try {
            if (sala.getNome() == null) {
                throw new IllegalArgumentException("O nome da sala é OBRIGATÓRIO");
            }
            if (sala.getPreco() <= 0) {
                throw new IllegalArgumentException("O valor deve ser MAIOR QUE ZERO");
            }
            if (sala.getDataExclusao() != null) {
                throw new IllegalArgumentException("Uma sala cadastrada não pode ser excluida no cadastro");
            }
            salaDAO.cadastrar(sala);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void alterar(Sala sala, long id){
        Sala salaExiste = buscaPorId(id);
        try {
            if (!Objects.equals(sala.getId(), salaExiste.getId())){
                throw new IllegalArgumentException("O ID não corresponte a um item existente");
            }
            if (sala.getNome() == null) {
                throw new IllegalArgumentException("O nome da sala é OBRIGATÓRIO");
            }
            if (sala.getPreco() <= 0) {
                throw new IllegalArgumentException("O valor deve ser MAIOR QUE ZERO");
            }
            if (sala.getDataExclusao() != null) {
                throw new IllegalArgumentException("Uma sala cadastrada não pode ser alterada para exclusão");
            }
            salaDAO.alterar(sala);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void excluir(long id){
        Sala salaExiste = buscaPorId(id);
        try {
            if (salaExiste.getId() == null){
                throw new IllegalArgumentException("O ID não corresponte a um item existente");
            }
            if (salaExiste.getDataExclusao() != null) {
                throw new IllegalArgumentException("Uma sala excluida não pode ser excluida novamente");
            }
            salaDAO.deletar(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
