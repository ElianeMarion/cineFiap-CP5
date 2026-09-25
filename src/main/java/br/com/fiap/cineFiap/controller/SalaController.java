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

    public SalaController() {
        this.service = new SalaService();
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Sala sala){
        try{
            service.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        var salaExistente = service.buscarPorId(id);
        if(salaExistente.getId() != null){
            return ResponseEntity.ok(salaExistente);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Sala>> salasEmCartaz(){
        if(service.listar() != null) {
            return ResponseEntity.ok(service.listar());
        }
        return ResponseEntity.notFound().build();
    }
//
//    @DeleteMapping
//    public void excluir ( Long id){
//
//            dao.excluir(id);
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Sala objeto){
        var sala = service.buscarPorId(id);
        if(Objects.equals(sala.getId(), objeto.getId())){
            service.alterar(id, objeto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/excluir/{ìd}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try{
            service.deletar(id);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
