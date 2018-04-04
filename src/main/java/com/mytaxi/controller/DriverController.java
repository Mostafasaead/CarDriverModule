package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.DriverEntity;
import com.mytaxi.exception.BusinessException;
import com.mytaxi.service.driver.DriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

	private final DriverService driverService;

	@Autowired
	public DriverController(final DriverService driverService) {
		this.driverService = driverService;
	}

	@GetMapping("/{driverId}")
	public DriverDTO getDriver(@Valid @PathVariable long driverId) throws BusinessException {
		return DriverMapper.makeDriverDTO(driverService.find(driverId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws BusinessException {
		DriverEntity driverDO = DriverMapper.makeDriverDO(driverDTO);
		return DriverMapper.makeDriverDTO(driverService.create(driverDO));
	}

	@DeleteMapping("/{driverId}")
	public void deleteDriver(@Valid @PathVariable long driverId) throws BusinessException {
		driverService.delete(driverId);
	}

	@PutMapping("/{driverId}")
	public void updateLocation(@Valid @PathVariable long driverId, @RequestParam double longitude,
			@RequestParam double latitude) throws BusinessException {
		driverService.updateLocation(driverId, longitude, latitude);
	}

	@GetMapping
	public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus) throws BusinessException {
		return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
	}

	@RequestMapping(value = "/{driverId}/selectCar", method = RequestMethod.POST)
	public ResponseEntity<DriverCarDTO> assignCarToDriver(@PathVariable long driverId,
			@RequestParam("carId") long carId) throws BusinessException {
		return new ResponseEntity<>(driverService.assignCarToDriver(driverId, carId), HttpStatus.OK);
	}

	@RequestMapping(value = "/{driverId}/deselectCar", method = RequestMethod.DELETE)
	public void unassignCarFromDriver(@PathVariable long driverId, @RequestParam("carId") long carId)
			throws BusinessException {
		driverService.unassignCarFromDriver(driverId, carId);
	}

	@RequestMapping(value = "/findByCarSpecs", method = RequestMethod.POST)
	public List<DriverDTO> findByCarSpecs(@RequestBody CarDTO carDTO) throws BusinessException {
		return driverService.findByCarCriterias(carDTO);
	}
}
