package com.mytaxi.criteria;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.entity.CarEntity;

import lombok.Data;

@Data
public class RatingCriteria implements Criteria {
	private Float value;

	public RatingCriteria(Float value) {
		this.value = value;
	}

	@Override
	public List<CarEntity> meets(List<CarEntity> cars) {
		if (null != value)
			return cars.stream().filter(car -> car.getRating() == value.floatValue()).collect(Collectors.toList());
		return Collections.emptyList();
	}

}
