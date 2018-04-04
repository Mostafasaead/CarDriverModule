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
public class ConvertibleCriteria implements Criteria {
	private Boolean value;

	public ConvertibleCriteria(Boolean value) {
		this.value = value;
	}

	@Override
	public List<CarEntity> meets(List<CarEntity> cars) {
		if (null != value)
			return cars.stream().filter(car -> car.getConvertible().equals(value)).collect(Collectors.toList());
		return Collections.emptyList();
	}

}
