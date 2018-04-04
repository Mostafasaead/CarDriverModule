package com.mytaxi.service;

import static org.mockito.Matchers.any;
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
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.entity.CarEntity;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarServiceImpl;

/**
 * @author Mostafa El-Gazzar.
 */
public class CarServiceTest extends TestData {
	@Mock
	private CarRepository carRepository;

	@Mock
	private ManufacturerRepository manufacturerRepository;

	@InjectMocks
	private CarServiceImpl carService;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(CarServiceImpl.class);
	}

	@Test
	public void testFindCarById() throws EntityNotFoundException {
		CarEntity car = CarMapper.makeCarEntity(getCarDTO());
		when(carRepository.findOne(any(Long.class))).thenReturn(car);
		carService.findCarById(any(Long.class));
		verify(carRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testFindAllCars() {
		List<CarEntity> cars = CarMapper.makeCarEntities(Collections.singletonList(getCarDTO()));
		when(carRepository.findAll()).thenReturn(cars);
		carService.findAllCars();
		verify(carRepository, times(1)).findAll();
	}

	@Test
	public void testCreate() throws EntityNotFoundException {
		CarDTO car = getCarDTO();
		CarEntity carEntity = CarMapper.makeCarEntity(getCarDTO());
		when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);
		carService.create(car);
		verify(carRepository, times(1)).save(any(CarEntity.class));
	}

	@Test
	public void testDelete() throws EntityNotFoundException {
		CarEntity car = CarMapper.makeCarEntity(getCarDTO());
		when(carRepository.findOne(any(Long.class))).thenReturn(car);
		carService.delete(1L);
		verify(carRepository, times(1)).findOne(any(Long.class));
	}
}
