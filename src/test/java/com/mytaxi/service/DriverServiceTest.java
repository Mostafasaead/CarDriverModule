package com.mytaxi.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.TestData;
import com.mytaxi.controller.CarController;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.DriverEntity;
import com.mytaxi.exception.BusinessException;
import com.mytaxi.service.car.CarServiceImpl;
import com.mytaxi.service.driver.DefaultDriverService;

public class DriverServiceTest extends TestData {
	@Mock
	private DefaultDriverService driverService;
	@Mock
	private CarServiceImpl carService;
	@InjectMocks
	private CarController carController;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(DefaultDriverService.class);
	}

	@Test
	public void testFindByDriverId() throws BusinessException {
		DriverDTO driverDTO = getDriverDTO();
		DriverEntity driverEntity = DriverMapper.makeDriverDO(driverDTO);
		driverService.find(1l);
		when(driverService.find(any(Long.class))).thenReturn(driverEntity);
		verify(driverService, times(1)).find(any(Long.class));
	}

	@Test
	public void testCreate() throws BusinessException {
		DriverDTO driverDTO = getDriverDTO();
		DriverEntity driverEntity = DriverMapper.makeDriverDO(driverDTO);
		when(driverService.create(any(DriverEntity.class))).thenReturn(driverEntity);
		driverService.create(driverEntity);
		verify(driverService, times(1)).create(any(DriverEntity.class));
	}

	@Test
	public void testDelete() throws BusinessException {
		doNothing().when(driverService).delete(any(Long.class));
		driverService.delete(any(Long.class));
		verify(driverService, times(1)).delete(any(Long.class));
	}

	@Test
	public void testUpdateLocation() throws BusinessException {
		doNothing().when(driverService).updateLocation(any(Long.class), any(Double.class), any(Double.class));
		driverService.updateLocation(any(Long.class), any(Double.class), any(Double.class));
		verify(driverService, times(1)).updateLocation(any(Long.class), any(Double.class), any(Double.class));
	}

	@Test
	public void testFindByOnlineStatus() {
		List<DriverEntity> drivers = Collections.singletonList(DriverMapper.makeDriverDO(getDriverDTO()));
		when(driverService.find(any(OnlineStatus.class))).thenReturn(drivers);
		driverService.find(any(OnlineStatus.class));
		verify(driverService, times(1)).find(any(OnlineStatus.class));
	}

	@Test
	public void testSelectCarByDriver() throws Exception {
		DriverEntity driverEntity = DriverMapper.makeDriverDO(getDriverDTO());
		CarDTO car = getCarDTO();
		when(driverService.find(any(Long.class))).thenReturn(driverEntity);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		driverService.assignCarToDriver(1L, 1L);
		driverService.find(1l);
		verify(driverService, times(1)).find(any(Long.class));
		carService.findCarById(1l);
		verify(carService, times(1)).findCarById(any(Long.class));
	}

	@Test
	public void testUnassignCarFromDriver() throws Exception {
		DriverEntity driverEntity = DriverMapper.makeDriverDO(getDriverDTO());
		CarDTO car = getCarDTO();
		when(driverService.find(any(Long.class))).thenReturn(driverEntity);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		driverService.unassignCarFromDriver(1L, 1L);
		driverService.find(1l);
		verify(driverService, times(1)).find(any(Long.class));
		carService.findCarById(1l);
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(driverService, times(1)).unassignCarFromDriver(any(Long.class), any(Long.class));
	}

	@Test
	public void testFindDriverByCarAttributes() throws BusinessException {
		CarDTO carDTO = getCarDTO();
		List<DriverDTO> drivers = Collections.singletonList(getDriverDTO());
		when(driverService.findByCarCriterias(any(CarDTO.class))).thenReturn(drivers);
		driverService.findByCarCriterias(carDTO);
		verify(driverService, times(1)).findByCarCriterias(any(CarDTO.class));
	}

}
