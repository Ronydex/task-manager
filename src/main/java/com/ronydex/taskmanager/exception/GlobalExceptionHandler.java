package com.ronydex.taskmanager.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ronydex.taskmanager.dto.ErrorResponseDTO;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(RecursoNoEncontradoException.class)
	public ResponseEntity<ErrorResponseDTO> manejarRecursoNoEncontrado(
			RecursoNoEncontradoException ex,
			HttpServletRequest request){
	ErrorResponseDTO errorNoEncontrado = new ErrorResponseDTO(
			LocalDateTime.now(),
			"404 NOT FOUND",
			ex.getMessage(),
			request.getRequestURI()
			);

	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorNoEncontrado);
			}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> manejarValidaciones(
			MethodArgumentNotValidException ex,
			HttpServletRequest request){

		Map<String,String> errores = new HashMap<>();

			for(FieldError error :  ex.getBindingResult().getFieldErrors()){
				errores.put(error.getField(), error.getDefaultMessage());
			}
		ErrorResponseDTO errorCampoNoValido = new ErrorResponseDTO(
				LocalDateTime.now(),
				"400 BAD REQUEST",
				ex.getMessage(),
				request.getRequestURI(),
				errores
				);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCampoNoValido);
		}
	/*
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> manejarErroresGenericos(
			Exception ex,
			HttpServletRequest request){
	ErrorResponseDTO errorGenerico = new ErrorResponseDTO(
			LocalDateTime.now(),
			"500_INTERNAL_SERVER_ERROR",
			"Ocurrió un error interno en el servidor,favor de reintentar o contactar al administrador",
			request.getRequestURI()
			);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorGenerico);
			}
*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> manejarErrorGenerico(Exception ex, HttpServletRequest request) {
    		Map<String, Object> error = new HashMap<>();
    		error.put("direccionURLFalla", request.getRequestURI());
    		error.put("estadoHttp", HttpStatus.INTERNAL_SERVER_ERROR);
    		error.put("horaDelError", LocalDateTime.now());
    // 💡 Esto te imprimirá la verdadera causa del error en pantalla:
   		 error.put("mensajeDeError", ex.getClass().getSimpleName() + ": " + ex.getMessage());
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
}
}
