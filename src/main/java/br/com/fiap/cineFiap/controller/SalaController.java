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

    private final SalaService service;

    public SalaController(){
        this.service = new SalaService();
    }

    private SalaDAO dao = new SalaDAO();


    public void cadastrar( Sala sala){

            dao.cadastrar(sala);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        var sala = service.buscarPorId(id);
        if (sala.getId() != null)
            return ResponseEntity.ok(sala);
        return ResponseEntity.notFound().build();



    }

    @GetMapping
    public List<Sala> salasEmCartaz(){
        return service.listar();
    }


    public void excluir ( Long id){

            dao.excluir(id);

    }

    public void alterar( Long id, Sala objeto){

            dao.alterar(objeto);
    }


    public void deletar(@PathVariable Long id){

            dao.deletar(id);


    }
}
