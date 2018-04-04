package com.mytaxi.repository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.DriverEntity;

/**
 * @author Mostafa El-Gazzar.
 */
public class DriverRepositoryTest extends RepositoryTest {
	private static final String USER_NAME = "driver02";

	@Autowired
	private DriverRepository driverRepository;

	@Test
	public void testDriverById() {
		DriverEntity driverEntity = driverRepository.findOne(1L);
		Assert.assertNotNull(driverEntity);
	}

	@Test
	public void testDriverByOnlineStatus() {
		List<DriverEntity> driverEntities = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
		Assert.assertThat(driverEntities, hasSize(7));
	}

	@Test
	public void testDriverByOfflineStatus() {
		List<DriverEntity> offlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.OFFLINE);
		Assert.assertThat(offlineDrivers, hasSize(7));
	}

	@Test
	public void testDriverByUsername() {
		DriverEntity driverEntity = driverRepository.findByUsername(USER_NAME);
		Assert.assertNotNull(driverEntity);
	}
}
