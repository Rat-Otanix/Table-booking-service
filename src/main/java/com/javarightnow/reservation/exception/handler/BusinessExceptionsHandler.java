package com.javarightnow.reservation.exception.handler;

import com.javarightnow.reservation.exception.DuplicateResourceException;
import com.javarightnow.reservation.exception.MethodArgumentNotValidException;
import com.javarightnow.reservation.exception.NoSuchResourceException;
import com.javarightnow.reservation.exception.ReservationConflictException;
import com.javarightnow.reservation.exception.domain.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * spring MessageSource could be used here to return message if needed.
 *
 * @author hadi
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class BusinessExceptionsHandler extends ResponseEntityExceptionHandler  implements IExceptionHandler {

    /**
     * when a resource(like a entity) already exists, we throw this exception.
     */
    @ExceptionHandler({DuplicateResourceException.class})
    protected ResponseEntity<Object> handleDuplicateResource(DuplicateResourceException ex, WebRequest request) {
        log.error("The resource already exists! {}", ex.getLocalizedMessage());

        ResponseError responseError = new ResponseError(CONFLICT);
        responseError.setMessage(ex.getLocalizedMessage());
        responseError.setErrorCode(ex.getCode());
        return buildResponseEntity(ex, responseError);
    }

    /**
     * If you face a time conflict in making a reservation, you can throw this exception.
     */
    @ExceptionHandler(ReservationConflictException.class)
    protected ResponseEntity<Object> handleBusinessConflict(ReservationConflictException ex,
                                                            WebRequest request) {
        ResponseError responseError = new ResponseError(CONFLICT);
        responseError.setMessage(ex.getLocalizedMessage());
        responseError.setErrorCode(ex.getCode());
        return buildResponseEntity(ex, responseError);
    }

    /**
     * Handles NoSuchResourceException.
     * Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     */
    @ExceptionHandler(NoSuchResourceException.class)
    protected ResponseEntity<Object> handleNoSuchResource(
            NoSuchResourceException ex) {
        log.error("The resource doesn't exist! {}", ex.getLocalizedMessage());

        ResponseError responseError = new ResponseError(NOT_FOUND);
        responseError.setMessage(ex.getLocalizedMessage());
        responseError.setErrorCode(ex.getCode());
        return buildResponseEntity(ex, responseError);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ResponseError responseError = new ResponseError(BAD_REQUEST);
        responseError.setMessage("Validation error");
        responseError.setErrorCode(ex.getCode());
        responseError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        responseError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(ex, responseError);
    }

}
