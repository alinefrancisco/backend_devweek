package com.dio_class.devweek.Controller;

import com.dio_class.devweek.Entity.Regiao;
import com.dio_class.devweek.Repository.RegiaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class RegiaoController {

    private final RegiaoRepository regiaoRepository;

    public RegiaoController(RegiaoRepository repository) {
        this.regiaoRepository = repository;
    }

    @GetMapping("/regiao")
    public ResponseEntity<?> findAllRegioes(){
        try{
            List<Regiao> lista = regiaoRepository.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*public List<Regiao> getRegiao(){
        return regiaoRepository.findAll();
    }*/

    @GetMapping("/regiao/{id}")
    public ResponseEntity<?> getRegiaoById(@PathVariable Long id){
        Optional regiaoEscolhidaOptional = regiaoRepository.findById(id);
        if(regiaoEscolhidaOptional.isPresent()){
            Object regiaoEscolhida = regiaoEscolhidaOptional.get();
            return new ResponseEntity<>(regiaoEscolhida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/regiao/{novo}")
    public Regiao putRegiao(@RequestBody Regiao newRegiao){
        return regiaoRepository.save(newRegiao);
    }

    @DeleteMapping("/regiao/remover/{id}")
    public void deleteRegiao(@PathVariable Long id){
        regiaoRepository.deleteById(id);
    }

}
