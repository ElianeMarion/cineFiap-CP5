package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private SalaDAO dao = new SalaDAO();

    private final SalaService salaService;

    public SalaController(SalaService salaService){
        this.salaService = salaService;
    }

    @GetMapping
    public List<Sala> listar(){
        return salaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = salaService.buscarPorId(id);

        if (sala == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sala);
    }

    public void cadastrar(Sala sala){
        dao.cadastrar(sala);
    }

    public List<Sala> salasEmCartaz(){
        return dao.listar();
    }

    public void excluir(Long id){
        dao.excluir(id);
    }

    public void alterar(Long id, Sala objeto){
        dao.alterar(objeto);
    }

    public void deletar(@PathVariable Long id){
        dao.deletar(id);
    }
}