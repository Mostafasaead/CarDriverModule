package com.mytaxi.service.user;

/**
 * @author Mostafa El-Gazzar.
 */
import com.mytaxi.datatransferobject.DriverDTO;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

	String authenticateUser(DriverDTO diver);

}
