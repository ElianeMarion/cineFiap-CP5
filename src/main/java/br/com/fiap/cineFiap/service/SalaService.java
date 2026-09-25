package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    private SalaDAO salaDAO = new SalaDAO();

    public List<Sala> listar(){return salaDAO.listar();}

    public Sala buscaPorId(long id) {return salaDAO.buscarPorId(id);}
}
