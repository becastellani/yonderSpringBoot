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

import com.yonder.model.Agendamento;
import com.yonder.repository.AgendamentoRepository;
import com.yonder.repository.querys.AgendamentoQueryRepository;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private AgendamentoQueryRepository agendamentoQueryRepository;
	
	@GetMapping
	public List<Agendamento> listar(@RequestParam(required = false) String dia, @RequestParam(required = false) String horario, @RequestParam(required = false) String feito) {
		
		if(dia != null || horario != null || feito != null) {
			System.out.print(feito);
			return agendamentoQueryRepository.agendamentoQuery(dia, horario, feito);
		}
		
		return agendamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Agendamento> listar(@PathVariable Integer id) {
		
		return agendamentoRepository.findById(id);
	}

	@PostMapping("/adicionar")
	public  ResponseEntity<String> adicionar(@RequestBody Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return ResponseEntity.ok("Empresa adicionada com Sucesso!");
	}
	
    @PutMapping("/{id}") // 
    public Agendamento atualizar(@PathVariable Integer id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoExistente = agendamentoRepository.findById(id);
        if (agendamentoExistente.isPresent()) {
            agendamento.setId(id); 
            return agendamentoRepository.save(agendamento);
        } else {
            throw new RuntimeException("Empresa não encontrada com o ID: " + id);
        }
    }

    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        agendamentoRepository.deleteById(id);
        return ResponseEntity.ok("Empresa excluída com sucesso!");
    }
}
