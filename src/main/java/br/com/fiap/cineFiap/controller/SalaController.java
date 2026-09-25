package br.com.fiap.cineFiap.controller;

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
    private SalaService salaService = new SalaService();


    @GetMapping
    public List<Sala> listar(){
        System.out.println("Teste se entra na Controller");
        return salaService.listar();
    }

    @GetMapping("/{id}") /*Buscar sala por ID CONTROLER*/
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        var sala = salaService.listarPorId(id);
        if(sala.getId() != null)
            return ResponseEntity.ok(sala);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> cadastroSala(@RequestBody Sala sala){
        try {
            salaService.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body("Funcionou com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar sala: " + e.getMessage());
        }
    }
    @PutMapping("/{id}") /*Alteração de dados com @PutMapping*/
    public ResponseEntity<Void> alterar(@PathVariable Long id,
                                        @RequestBody Sala sala){
        var sala1 = salaService.listarPorId(id);
        if (Objects.equals(sala.getId(),sala1.getId())){
            salaService.alterar (sala);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<Sala> eLogica(@PathVariable Long id){
        var sala = salaService.listarPorId(id);
        if (sala.getId()!= null) {
            salaService.deletar(id);
            return ResponseEntity.ok(sala);
        }
        return ResponseEntity.notFound().build();
    }

}