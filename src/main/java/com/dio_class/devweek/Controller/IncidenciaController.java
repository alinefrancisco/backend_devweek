package com.dio_class.devweek.Controller;

import com.dio_class.devweek.Entity.IncidenciaExame;
import com.dio_class.devweek.Repository.IncidenciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class IncidenciaController {

    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaController(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    @GetMapping("/incidencia")
    public ResponseEntity<List<IncidenciaExame>> findIncidencias(){
        List<IncidenciaExame> listaIncidencia = incidenciaRepository.findAll();
        if (listaIncidencia.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<>(listaIncidencia, HttpStatus.OK);
    }

    @GetMapping("/incidencia/{id}")
    public ResponseEntity<IncidenciaExame> findIncidenciasById(@PathVariable Long id){
        Optional<IncidenciaExame> incidenciaOptional = incidenciaRepository.findById(id);
        if (incidenciaOptional.isPresent()){
            IncidenciaExame incidenciaUnid = incidenciaOptional.get();
            return new ResponseEntity<>(incidenciaUnid, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/incidenciaexame/{novo}")
    public IncidenciaExame putIncidencia(@RequestBody IncidenciaExame newIncidencia){
        return incidenciaRepository.save(newIncidencia);
    }

    @DeleteMapping("/incidenciaexame/delete/{id}")
    public void deleteIncidencia(@PathVariable Long id){
        incidenciaRepository.deleteById(id);
    }
}
