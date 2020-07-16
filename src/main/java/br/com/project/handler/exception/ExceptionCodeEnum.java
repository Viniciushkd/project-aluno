package br.com.project.handler.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionCodeEnum {

	ALUNO_INEXISTENTE("Aluno não encontrado.", HttpStatus.CONFLICT);
	
	
	private String description;
	private HttpStatus httpStatus;
	
	private ExceptionCodeEnum(String description, org.springframework.http.HttpStatus httpStatus) {
		this.description = description;
		this.httpStatus = httpStatus;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
