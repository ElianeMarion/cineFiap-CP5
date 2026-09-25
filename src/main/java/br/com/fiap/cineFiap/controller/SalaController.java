package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private SalaDAO dao = new SalaDAO();
    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }

    @PostMapping
    public void cadastrar( Sala sala){

            dao.cadastrar(sala);

    }

    @GetMapping("{/id}")
    public Sala buscarPorId( Long id){
        return  dao.buscarPorId(id);


    }

    @GetMapping
    public ResponseEntity<List<Sala>> salasEmCartaz(){
        if(service.listar() != null) {
            return ResponseEntity.ok(service.listar());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public void excluir ( Long id){

            dao.excluir(id);

    }

    @PutMapping
    public void alterar( Long id, Sala objeto){

            dao.alterar(objeto);
    }

    @PutMapping
    public void deletar(@PathVariable Long id){

            dao.deletar(id);


    }
}
