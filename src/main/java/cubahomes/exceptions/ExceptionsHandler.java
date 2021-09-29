package cubahomes.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cubahomes.model.ErrorMessage;


@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{


	@ExceptionHandler(value = {IllegalStateException.class})
	public ResponseEntity<ErrorMessage> customExceptionHandler(Exception e){
		
		System.out.println("syso " + e.getMessage());
		ErrorMessage message = new ErrorMessage("hola", new Date(), HttpStatus.BAD_REQUEST);
		System.out.println(message.toString());
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	
}

