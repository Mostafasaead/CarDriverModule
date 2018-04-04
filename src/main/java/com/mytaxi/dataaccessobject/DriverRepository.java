package com.mytaxi.dataaccessobject;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.CarEntity;
import com.mytaxi.entity.DriverEntity;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverEntity, Long> {

	List<DriverEntity> findByOnlineStatus(OnlineStatus onlineStatus);

	List<DriverEntity> findByCars(CarEntity carEntity);

	@Query(value = "select driver.* from driver join car on driver.driver_id=car.driver_id where car.car_id in ?1", nativeQuery = true)
	List<DriverEntity> findByCarIds(List<Long> carIds);

	DriverEntity findByUsername(String username);
}
