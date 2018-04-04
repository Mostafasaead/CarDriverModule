package com.mytaxi.dataaccessobject;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.entity.CarEntity;

public interface CarRepository extends CrudRepository<CarEntity, Long> {

}
