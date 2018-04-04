package com.mytaxi.service.car;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.entity.CarEntity;
import com.mytaxi.entity.ManufacturerEntity;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public CarDTO findCarById(Long carId) throws EntityNotFoundException {

		return CarMapper.makeCarDTO(carRepository.findOne(carId));
	}

	@Override
	public List<CarDTO> findAllCars() {
		return CarMapper.makeCarDTOList((carRepository.findAll()));
	}

	@Override
	public CarDTO create(CarDTO carDTO) throws EntityNotFoundException {
		CarEntity carEntity = CarMapper.makeCarEntity(carDTO);
		return CarMapper.makeCarDTO(carRepository.save(carEntity));
	}

	@Override
	@Transactional
	public void update(CarDTO carDTO) throws EntityNotFoundException {
		CarEntity updatedCar = isExistingCar(carDTO.getId());
		String manufacturer = null;
		if (null == carDTO.getManufacturer())
			manufacturer = updatedCar.getManufacturer().getName();
		updatedCar.setManufacturer(isExistingManufacturer(manufacturer));
		updatedCar.setConvertible(carDTO.getConvertible());
		updatedCar.setEngineType(carDTO.getEngineType());
		updatedCar.setLicensePlate(carDTO.getLicensePlate());
		updatedCar.setRating(carDTO.getRating());
		updatedCar.setSeatCount(carDTO.getSeatCount());

	}

	@Override
	@Transactional
	public void delete(Long carId) throws EntityNotFoundException {
		CarEntity deletedCar = isExistingCar(carId);
		deletedCar.setDeleted(Boolean.TRUE);
	}

	private CarEntity isExistingCar(Long carId) throws EntityNotFoundException {
		CarEntity carEntity = carRepository.findOne(carId);
		if (null != carEntity) {
			return carEntity;

		}
		throw new EntityNotFoundException("This CarId " + carId + "is not exist");
	}

	private ManufacturerEntity isExistingManufacturer(String name) throws EntityNotFoundException {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findByName(name);
		if (null != manufacturerEntity) {
			return manufacturerEntity;

		}
		throw new EntityNotFoundException("This Manufacturer " + name + "is not exist");
	}

}
