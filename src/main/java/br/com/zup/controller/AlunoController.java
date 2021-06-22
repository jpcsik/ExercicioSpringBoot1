package br.com.zup.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.controller.dto.DetalheAlunoDto;
import br.com.zup.controller.form.AlunoForm;
import br.com.zup.modelo.Aluno;
import br.com.zup.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetalheAlunoDto> cadastro(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {
		
		Aluno aluno = new Aluno(form);
		alunoRepository.save(aluno);
		DetalheAlunoDto alunoDto = new DetalheAlunoDto(aluno);
		
		URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(alunoDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheAlunoDto> buscarAluno(@PathVariable Long id){
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(aluno.isPresent()) {
			return ResponseEntity.ok(new DetalheAlunoDto(aluno.get()));
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	
}
