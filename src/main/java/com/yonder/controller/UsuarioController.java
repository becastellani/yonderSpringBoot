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


import com.yonder.model.Usuario;
import com.yonder.repository.UsuarioRepository;
import com.yonder.repository.querys.UsuarioQueryRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioQueryRepository usuarioQueryRepository;
	
	@GetMapping
	public List<Usuario> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String cpf) {
		if(nome != null || cpf != null) {
			return usuarioQueryRepository.usuarioQuery(nome, cpf);
		}
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> listar(@PathVariable Integer id) {
		
		return usuarioRepository.findById(id);
	}

	@PostMapping("/adicionar") 
	public  ResponseEntity<String> adicionar(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
		return ResponseEntity.ok("Usuario adicionado com Sucesso!");
	}
	
    @PutMapping("/{id}") // 
    public ResponseEntity<String> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuario.setId(id); 
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuario atualizado com sucesso");
            		
        } else {
            throw new RuntimeException("Usuario nao encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuario excluído com sucesso!");
    }
}
