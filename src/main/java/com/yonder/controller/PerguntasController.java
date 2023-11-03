package com.yonder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yonder.model.Perguntas;
import com.yonder.repository.querys.PerguntasQueryRepository;
import com.yonder.repository.PerguntasRepository;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {
	@Autowired
	private PerguntasRepository perguntasRepository;
	@Autowired
	private PerguntasQueryRepository perguntasQueryRepository;
	
	@GetMapping
	public List<Perguntas> listar(@RequestParam(required = false) String cabecalho, @RequestParam(required = false) String tipo_prova, @RequestParam(required = false) String nivel) {
		if(cabecalho != null || tipo_prova != null || nivel != null) {
			return perguntasQueryRepository.perguntasQuery(cabecalho, tipo_prova, nivel);
		}
		
		return perguntasRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Perguntas> listar(@PathVariable Integer id) {
		
		return perguntasRepository.findById(id);
	}

	@PostMapping("/adicionar") 
	public  ResponseEntity<String> adicionar(@RequestBody Perguntas pergunta) {
		perguntasRepository.save(pergunta);
		return ResponseEntity.ok("Pergunta adicionada com Sucesso!");
	}
	
    @PutMapping("/{id}") // 
    public Perguntas atualizar(@PathVariable Integer id, @RequestBody Perguntas pergunta) {
        Optional<Perguntas> perguntaExistente = perguntasRepository.findById(id);
        if (perguntaExistente.isPresent()) {
            pergunta.setId(id); 
            return perguntasRepository.save(pergunta);
        } else {
            throw new RuntimeException("Pergunta não encontrada com o ID: " + id);
        }
    }

    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        perguntasRepository.deleteById(id);
        return ResponseEntity.ok("Pergunta excluída com sucesso!");
    }
}
