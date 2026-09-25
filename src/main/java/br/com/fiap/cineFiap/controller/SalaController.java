package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private SalaService service = new SalaService();

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Sala sala){
        try {
            service.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body("sala cadastro");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("deu erro no cadastro: " + e.getMessage());
        }

    }



    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {

        try {
            Sala sala = service.buscarPorId(id);
            return ResponseEntity.ok(sala);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }




    public List<Sala> salasEmCartaz(){
        return service.listar();
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Sala sala) {
        try {
            sala.setId(id);
            service.alterar(sala);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return ResponseEntity.ok(service.listar());
    }



}
