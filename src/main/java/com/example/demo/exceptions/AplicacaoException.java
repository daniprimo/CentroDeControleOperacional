package com.example.demo.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.DefaultError;
import com.example.demo.dto.StandartError;

@ControllerAdvice
public class AplicacaoException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		System.out.println("Entro no Erro do Handler");
		
		DefaultError error = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Teve algum error interno por gentileza contatar o suporte");
		
		
		return new ResponseEntity(error, HttpStatus.BAD_GATEWAY);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandartError> coletivoNaoEncontrado(EntityNotFoundException e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Sem registro do coletivo citado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ColetivoNaoFoiSalvoException.class)
	public ResponseEntity<StandartError> coletivoNaoFoiSalvo(ColetivoNaoFoiSalvoException e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Verifique se todos os campos estão completos");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}



}
