package com.mytaxi.dataaccessobject;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.entity.DriverCarEntity;

public interface DriverCarRepository extends CrudRepository<DriverCarEntity, Long> {
	DriverCarEntity findByDriverIdAndCarId(final Long driverId, final Long carId);

}
