package br.com.project.service.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String erro;
	private Long timestamp;
	
	public MessageDTO(String error) {
		this.erro = error;
		this.timestamp = System.currentTimeMillis();
	}

	public String getErro() {
		return erro;
	}
	public Long getTimestamp() {
		return timestamp;
	}
}