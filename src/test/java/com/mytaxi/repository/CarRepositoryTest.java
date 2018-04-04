package com.mytaxi.repository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.entity.CarEntity;

/**
 * @author Mostafa El-Gazzar.
 */
public class CarRepositoryTest extends RepositoryTest {
	@Autowired
	private CarRepository carRepository;

	@Test
	public void testDriverById() {
		CarEntity carEntity = carRepository.findOne(1L);
		Assert.assertNotNull(carEntity);
	}

	@Test
	public void testAllCars() {
		List<CarEntity> carEntities = Lists.newArrayList(carRepository.findAll());
		Assert.assertThat(carEntities, hasSize(4));
	}
}
