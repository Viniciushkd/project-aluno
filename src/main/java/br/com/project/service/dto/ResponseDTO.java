package br.com.project.service.dto;

public class ResponseDTO<T> {

	public T data;

	public ResponseDTO(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
	
}
