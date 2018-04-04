package com.mytaxi.exception;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There is no matched cars")
public class MissmatchCriteriaException extends BusinessException {

	private static final long serialVersionUID = -7359067178143231257L;

	public MissmatchCriteriaException(String message) {
		super(message);
	}

}
