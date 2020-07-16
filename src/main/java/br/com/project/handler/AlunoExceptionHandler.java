package br.com.project.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import br.com.project.handler.exception.AlunoException;
import br.com.project.service.dto.MessageDTO;

@ControllerAdvice
public class AlunoExceptionHandler {

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(AlunoException.class)
	public ResponseEntity<Object> handleAlunoExeption(final AlunoException dnaExp, final WebRequest request) {
		return this.handleExceptionInternal(dnaExp, new MessageDTO(dnaExp.getCodeEnum().getDescription()),
				new HttpHeaders(), dnaExp.getCodeEnum().getHttpStatus(), request);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleAPIException(final MethodArgumentNotValidException ex) {
		List<String> messages = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fes = bindingResult.getFieldErrors();
		for (FieldError fe : fes) {
			messages.add(fe.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(messages);
	}
}
