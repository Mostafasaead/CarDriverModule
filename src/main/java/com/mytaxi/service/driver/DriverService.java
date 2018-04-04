package com.mytaxi.service.driver;

import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.DriverEntity;
import com.mytaxi.exception.BusinessException;

public interface DriverService {

	DriverEntity find(Long driverId) throws BusinessException;

	DriverEntity create(DriverEntity driverDO) throws BusinessException;

	void delete(Long driverId) throws BusinessException;

	void updateLocation(long driverId, double longitude, double latitude) throws BusinessException;

	List<DriverEntity> find(OnlineStatus onlineStatus);

	DriverCarDTO assignCarToDriver(Long driverId, Long CarId) throws BusinessException;

	void unassignCarFromDriver(Long driverId, Long carId) throws BusinessException;

	List<DriverDTO> findByCarCriterias(CarDTO carDTO) throws BusinessException;

}
