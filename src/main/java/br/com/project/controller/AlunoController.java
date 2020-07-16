package br.com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.service.AlunoService;
import br.com.project.service.dto.AlunoDTO;
import br.com.project.service.dto.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/aluno")
@Api(value = "Aluno Controller")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@Autowired
	public AlunoController(AlunoService service) {
		super();
		this.service = service;
	}

	@PostMapping
	@ApiOperation(value = "Cadastro de Aluno.")
	public ResponseEntity<Object> save(@Valid @RequestBody AlunoDTO dto){
		service.save(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza Aluno.")
	public ResponseEntity<Object> update(@Valid @RequestBody AlunoDTO dto, @PathVariable(required = true) long id ){
		service.update(id, dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	@ApiOperation(value = "Lista Aluno.")
	public ResponseEntity<ResponseDTO<List<AlunoDTO>>> get(){
		final List<AlunoDTO> list = service.get();
		return new ResponseEntity<>(new ResponseDTO<>(list), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca Aluno.")
	public ResponseEntity<ResponseDTO<AlunoDTO>> get(@PathVariable long id){
		final AlunoDTO dto = service.get(id);
		return new ResponseEntity<>(new ResponseDTO<>(dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui Aluno.")
	public ResponseEntity<ResponseDTO<AlunoDTO>> delete(@PathVariable long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
