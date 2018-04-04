package com.mytaxi.criteria;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.ArrayList;
import java.util.List;

import com.mytaxi.entity.CarEntity;
import com.mytaxi.exception.MissmatchCriteriaException;

public class AndOrCriteria implements Criteria {
	private Criteria[] criterias;

	public AndOrCriteria(Criteria... criterias) {
		this.criterias = criterias;
	}

	@Override
	public List<CarEntity> meets(List<CarEntity> cars) throws MissmatchCriteriaException {
		List<CarEntity> filteredCars = new ArrayList<>();
		List<CarEntity> tempCars;
		for (Criteria criteria : criterias) {
			if (null != criteria) {
				tempCars = criteria.meets(cars);
				if (tempCars.isEmpty())
					filteredCars.addAll(tempCars);
				else
					filteredCars = tempCars;
			}
		}
		return filteredCars;
	}

}
