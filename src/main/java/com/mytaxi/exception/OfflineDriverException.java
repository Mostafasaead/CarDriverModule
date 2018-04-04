package com.mytaxi.exception;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Sorry, This driver is Offline")
public class OfflineDriverException extends BusinessException {
	public OfflineDriverException(String message) {
		super(message);
	}

}
