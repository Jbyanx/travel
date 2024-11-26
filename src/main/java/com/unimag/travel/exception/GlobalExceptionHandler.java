package com.unimag.travel.exception;

import com.unimag.travel.dto.response.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex,
                                                                    HttpServletRequest request) {
        int status = HttpStatus.FORBIDDEN.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Acceso denegado: No tienes permisos para realizar esta acción.",
                ex.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    // Excepción para problemas de autenticación (401 Unauthorized)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException ex,
                                                                      HttpServletRequest request) {
        int status = HttpStatus.UNAUTHORIZED.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Autenticación fallida: Verifica tus credenciales e intenta nuevamente.",
                ex.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException dataIntegrityViolationException,
                                                                               HttpServletRequest request) {
        int status = HttpStatus.CONFLICT.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, hay un conflicto de integridad de datos, verifique si existen campos unicos o referencias a otras tablas",
                dataIntegrityViolationException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> HttpMessageNotReadableHandler(HttpMessageNotReadableException httpMessageNotReadableException,
                                                                      HttpServletRequest request){
        int status = HttpStatus.BAD_REQUEST.value();

        ErrorMessage errorMessage = new ErrorMessage(
            status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Hubo un problema con el formato de los datos enviados, " +
                        "Por favor, revise la estructura del mensaje y vuelva a intentarlo",
                httpMessageNotReadableException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ErrorMessage> httpMediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException,
                                                                         HttpServletRequest request){
        int status = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
        String mediaTypeSent = httpMediaTypeNotSupportedException.getContentType().toString();
        String mediaTypesSupported = httpMediaTypeNotSupportedException.getSupportedMediaTypes().toString();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "El tipo de medios " +mediaTypeSent+" no esta soportado por el servidor, mediatypes validos:" +
                        mediaTypesSupported,
                httpMediaTypeNotSupportedException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );

        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> httpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                                             HttpServletRequest request){
        int status = HttpStatus.METHOD_NOT_ALLOWED.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "error, el endpoint no esta configurado para recibir ese tipo de metodo",
                httpRequestMethodNotSupportedException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
                                                                      HttpServletRequest request)
    {
        int status = HttpStatus.BAD_REQUEST.value();
        String property = methodArgumentTypeMismatchException.getPropertyName();
        String value = methodArgumentTypeMismatchException.getValue().toString();
        String dataType = methodArgumentTypeMismatchException.getRequiredType().getTypeName();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el valor "+value+" enviado en la propiedad "
                        +property+" espera un tipo de dato "+dataType+" !",
                methodArgumentTypeMismatchException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidHandler(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                      HttpServletRequest request){
        int status = HttpStatus.BAD_REQUEST.value();

        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        List<String> details = errors.stream().map( error -> {
            if(error instanceof FieldError fieldError){
                return fieldError.getField()+ ": "+fieldError.getDefaultMessage();
            }
            return error.getDefaultMessage();
        }).toList();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el argumento enviado es invalido, verifique sus restricciones!",
                methodArgumentNotValidException.getMessage(),
                LocalDateTime.now(ZoneId.systemDefault()),
                details
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundHandler(ResourceNotFoundException ex,
                                                              HttpServletRequest request) {

        LocalDateTime timestamp = LocalDateTime.now(ZoneId.systemDefault());

        if(ex instanceof ClienteNotFoundException clienteNotFoundException){//cliente
            return handleClienteNotFoundException(clienteNotFoundException, request,  timestamp);

        } else if(ex instanceof AerolineaNotFoundException aerolineaNotFoundException){//aerolinea
            return handleAerolineaNotFoundException(aerolineaNotFoundException, request,  timestamp);

        }else if(ex instanceof AeropuertoNotFoundException aeropuertoNotFoundException){//aeropuerto
            return handleAeropuertoNotFoundException(aeropuertoNotFoundException, request,  timestamp);

        }else if(ex instanceof EscalaNotFoundException escalaNotFoundException){//escala
            return handleEscalaNotFoundException(escalaNotFoundException, request,  timestamp);

        }else if(ex instanceof ReservaNotFoundException reservaNotFoundException){//reserva
            return handleReservaNotFoundException(reservaNotFoundException, request,  timestamp);

        }else if(ex instanceof VueloNotFoundException vueloNotFoundException){//vuelo
            return handleVueloNotFoundException(vueloNotFoundException, request, timestamp);

        }else {
            return handleResourceNotFoundException(ex, request, timestamp);//recurso generico
        }
    }

    private ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                         HttpServletRequest request,
                                                                         LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el recurso solicitado no existe!",
                ex.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleVueloNotFoundException(VueloNotFoundException vueloNotFoundException,
                                                                      HttpServletRequest request,
                                                                      LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el vuelo solicitado no existe!",
                vueloNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleReservaNotFoundException(ReservaNotFoundException reservaNotFoundException,
                                                                        HttpServletRequest request,
                                                                        LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, la reserva solicitada no existe!",
                reservaNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleEscalaNotFoundException(EscalaNotFoundException escalaNotFoundException,
                                                                       HttpServletRequest request,
                                                                       LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, la escala solicitada no existe!",
                escalaNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleAeropuertoNotFoundException(AeropuertoNotFoundException aeropuertoNotFoundException,
                                                                           HttpServletRequest request,
                                                                           LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el aeropuerto solicitado no existe!",
                aeropuertoNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleAerolineaNotFoundException(AerolineaNotFoundException aerolineaNotFoundException,
                                                                          HttpServletRequest request,
                                                                          LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, la aerolinea solicitada no existe!",
                aerolineaNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleClienteNotFoundException(ClienteNotFoundException clienteNotFoundException,
                                                                        HttpServletRequest request,
                                                                        LocalDateTime timestamp) {
        int status = HttpStatus.NOT_FOUND.value();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Error, el cliente solicitado no existe!",
                clienteNotFoundException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
