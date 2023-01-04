package com.example.demo.entities.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.DefaultError;
import com.example.demo.dto.StandartError;
import com.example.demo.universal.textos.Exceptions;

@ControllerAdvice
public class AplicacaoException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		System.out.println("Entro no Erro do Handler");

		DefaultError error = new DefaultError(HttpStatus.BAD_GATEWAY.value(), Exceptions.MENSSAGEM_DE_ERRO_PRINCIPAL);

		return new ResponseEntity(error, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandartError> coletivoNaoEncontrado(EntityNotFoundException e, HttpServletRequest request) {
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(Exceptions.TEXTO_ERROR_COLETIVO_NAO_ENCONTRADO);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ColetivoNaoFoiSalvoException.class)
	public ResponseEntity<StandartError> coletivoNaoFoiSalvo(ColetivoNaoFoiSalvoException e,
			HttpServletRequest request) {
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(Exceptions.TEXTO_ERROR_COLETIVO_NAO_SALVO);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ColetivoComPlacaCitadaJaExiste.class)
	public ResponseEntity<StandartError> ColetivoComPlcaExistente(ColetivoComPlacaCitadaJaExiste e,
			HttpServletRequest request) {
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(Exceptions.TEXTO_ERROR_PLACA_EXISTENTE);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ColetivoComDocumentoCitadaJaExiste.class)
	public ResponseEntity<StandartError> ColetivoComDocumentoExistente(ColetivoComDocumentoCitadaJaExiste e,
			HttpServletRequest request) {
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(Exceptions.TEXTO_ERROR_DOCUMENTO_EXISTENTE);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ColetivoComPrefixoCitadoJaExiste.class)
	public ResponseEntity<StandartError> ColetivoComPrefixoExistente(ColetivoComPrefixoCitadoJaExiste e,
			HttpServletRequest request) {
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(Exceptions.TEXTO_ERROR_PREFIXO_EXISTENTE);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
