package com.mytaxi.dataaccessobject;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.entity.ManufacturerEntity;

public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Long> {

	ManufacturerEntity findByName(String name);

}
