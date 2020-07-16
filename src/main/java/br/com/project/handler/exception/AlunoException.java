package br.com.project.handler.exception;

public class AlunoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private ExceptionCodeEnum codeEnum;
	
	public AlunoException(final ExceptionCodeEnum codeEnum, Throwable arg0) {
		super(arg0);
		this.codeEnum = codeEnum;
	}

	public AlunoException(ExceptionCodeEnum codeEnum) {
		super();
		this.codeEnum = codeEnum;
	}

	public ExceptionCodeEnum getCodeEnum() {
		return codeEnum;
	}
}
