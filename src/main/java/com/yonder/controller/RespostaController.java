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
import com.yonder.model.Resposta;
import com.yonder.repository.RespostaRepository;
import com.yonder.repository.querys.RespostaQueryRepository;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	@Autowired
	private RespostaRepository respostaRepository;
	@Autowired
	private RespostaQueryRepository respostaQueryRepository; 
	
	@GetMapping
	public List<Resposta> listar(@RequestParam(required = false) String texto, @RequestParam(required = false) String correta) {
		if(texto != null || correta != null) {
			
			return respostaQueryRepository.respostaQuery(texto, correta);
		}
		
		return respostaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Resposta> listar(@PathVariable Integer id) {
		
		return respostaRepository.findById(id);
	}

	@PostMapping("/adicionar") 
	public  ResponseEntity<String> adicionar(@RequestBody Resposta resposta) {
		respostaRepository.save(resposta);
		return ResponseEntity.ok("Resposta adicionada com Sucesso!");
	}
	
    @PutMapping("/{id}") // 
    public Resposta atualizar(@PathVariable Integer id, @RequestBody Resposta resposta) {
        Optional<Resposta> respostaExistente = respostaRepository.findById(id);
        if (respostaExistente.isPresent()) {
            resposta.setId(id); 
            return respostaRepository.save(resposta);
        } else {
            throw new RuntimeException("Resposta nao encontrada com o ID: " + id);
        }
    }

    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        respostaRepository.deleteById(id);
        return ResponseEntity.ok("Resposta excluída com sucesso!");
    }
	

}
