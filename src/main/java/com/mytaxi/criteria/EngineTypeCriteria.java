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
public class EngineTypeCriteria implements Criteria {
	private String value;

	public EngineTypeCriteria(String value) {
		this.value = value;
	}

	@Override
	public List<CarEntity> meets(List<CarEntity> cars) {
		if (null != value)
			return cars.stream().filter(car -> car.getEngineType().equals(value)).collect(Collectors.toList());
		return Collections.emptyList();
	}

}
