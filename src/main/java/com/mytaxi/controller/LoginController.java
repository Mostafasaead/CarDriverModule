package com.mytaxi.controller;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.exception.BusinessException;
import com.mytaxi.service.user.UserDetailsService;

@RestController
@RequestMapping("v1/user")
public class LoginController {
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> authenticateUser(@RequestBody DriverDTO diver) throws BusinessException {
		return new ResponseEntity<>(userDetailsService.authenticateUser(diver), HttpStatus.CREATED);
	}
}
