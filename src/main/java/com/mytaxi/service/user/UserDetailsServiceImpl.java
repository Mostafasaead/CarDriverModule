package com.mytaxi.service.user;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverDTO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) driverRepository.findByUsername(username);
	}

	public String authenticateUser(DriverDTO driver) {
		// TODO : create method to authenticate the user then return JWT(Json web token)
		return null;

	}

}
