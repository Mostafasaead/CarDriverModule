package com.mytaxi.criteria;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.List;

import com.mytaxi.entity.CarEntity;
import com.mytaxi.exception.MissmatchCriteriaException;

public interface Criteria {
	List<CarEntity> meets(List<CarEntity> cars) throws MissmatchCriteriaException;

}
