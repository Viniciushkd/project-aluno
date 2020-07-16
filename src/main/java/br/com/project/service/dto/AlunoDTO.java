package br.com.project.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.project.service.dto.valid.IdadeConstraint;

public class AlunoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	@NotEmpty(message = "Nome não pode ser vazio.")
	private String nome;
	@IdadeConstraint
	private Integer idade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
