package com.yonder.controller;

import com.yonder.model.Empresa;
import com.yonder.model.Perguntas;
import com.yonder.repository.EmpresaRepository;
import com.yonder.repository.querys.EmpresaQueryRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private EmpresaQueryRepository empresaQueryRepository;
	
	@GetMapping
	public List<Empresa> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String cnpj) {
		if(nome != null || cnpj != null) {
			return empresaQueryRepository.empresaQuery(nome, cnpj);
		}
		return empresaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Empresa> listar(@PathVariable Integer id) {
		
		return empresaRepository.findById(id);
	}
	
	@PostMapping("/adicionar") 
	public  ResponseEntity<String> adicionar(@RequestBody Empresa empresa) {
		empresaRepository.save(empresa);
		return ResponseEntity.ok("Empresa adicionada com Sucesso!");
	}
	
    @PutMapping("/{id}") // 
    public Empresa atualizar(@PathVariable Integer id, @RequestBody Empresa empresa) {
        Optional<Empresa> empresaExistente = empresaRepository.findById(id);
        if (empresaExistente.isPresent()) {
            empresa.setId(id); 
            return empresaRepository.save(empresa);
        } else {
            throw new RuntimeException("Empresa não encontrada com o ID: " + id);
        }
    }

    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        empresaRepository.deleteById(id);
        return ResponseEntity.ok("Empresa excluída com sucesso!");
    }
}
