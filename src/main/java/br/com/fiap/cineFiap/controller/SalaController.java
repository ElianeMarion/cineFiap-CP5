package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.exceptions.FilmeNaoExisteException;
import br.com.fiap.cineFiap.models.Sala;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private SalaDAO dao = new SalaDAO();

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Sala sala){
        try {
            dao.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body("sala cadastro");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("deu erro no cadastro: " + e.getMessage());
        }

    }



    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = dao.buscarPorId(id);

        if (sala.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sala);
    }

    public List<Sala> salasEmCartaz(){
        return dao.listar();
    }


    public void excluir ( Long id){

            dao.excluir(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Long id, @RequestBody Sala sala) {
        try {
            sala.setId(id);
            dao.alterar(sala);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    public void deletar(@PathVariable Long id){

            dao.deletar(id);


    }

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return ResponseEntity.ok(dao.listar());
    }



}
