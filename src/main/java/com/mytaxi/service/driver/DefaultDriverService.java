package com.mytaxi.service.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.criteria.AndOrCriteria;
import com.mytaxi.criteria.ConvertibleCriteria;
import com.mytaxi.criteria.EngineTypeCriteria;
import com.mytaxi.criteria.RatingCriteria;
import com.mytaxi.criteria.SeatCountCriteria;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.CarEntity;
import com.mytaxi.entity.DriverCarEntity;
import com.mytaxi.entity.DriverEntity;
import com.mytaxi.exception.BusinessException;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OfflineDriverException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

	private final DriverRepository driverRepository;
	private final CarRepository carRepository;
	private final DriverCarRepository driverCarRepository;

	public DefaultDriverService(final DriverRepository driverRepository, final CarRepository carRepository,
			final DriverCarRepository driverCarRepository) {
		this.driverRepository = driverRepository;
		this.carRepository = carRepository;
		this.driverCarRepository = driverCarRepository;
	}

	/**
	 * Selects a driver by id.
	 *
	 * @param driverId
	 * @return found driver
	 * @throws EntityNotFoundException
	 *             if no driver with the given id was found.
	 */
	@Override
	public DriverEntity find(Long driverId) throws BusinessException {
		return findDriverChecked(driverId);
	}

	/**
	 * Creates a new driver.
	 *
	 * @param driverDO
	 * @return
	 * @throws ConstraintsViolationException
	 *             if a driver already exists with the given username, ... .
	 */
	@Override
	public DriverEntity create(DriverEntity driverDO) throws BusinessException {
		DriverEntity driver;
		try {
			driver = driverRepository.save(driverDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to driver creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return driver;
	}

	/**
	 * Deletes an existing driver by id.
	 *
	 * @param driverId
	 * @throws EntityNotFoundException
	 *             if no driver with the given id was found.
	 */
	@Override
	@Transactional
	public void delete(Long driverId) throws BusinessException {
		DriverEntity driverDO = findDriverChecked(driverId);
		driverDO.setDeleted(true);
	}

	/**
	 * Update the location for a driver.
	 *
	 * @param driverId
	 * @param longitude
	 * @param latitude
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public void updateLocation(long driverId, double longitude, double latitude) throws BusinessException {
		DriverEntity driverDO = findDriverChecked(driverId);
		driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
	}

	/**
	 * Find all drivers by online state.
	 *
	 * @param onlineStatus
	 */
	@Override
	public List<DriverEntity> find(OnlineStatus onlineStatus) {
		return driverRepository.findByOnlineStatus(onlineStatus);
	}

	/**
	 * Assign a car to a driver
	 *
	 * @param driverId
	 *            & carId
	 */
	@Override
	@Transactional
	public DriverCarDTO assignCarToDriver(Long driverId, Long carId) throws BusinessException {
		DriverEntity driverEntity;
		CarEntity carEntity;
		driverEntity = driverRepository.findOne(driverId);
		carEntity = carRepository.findOne(carId);
		if (null != driverEntity && null != carEntity && driverEntity.getOnlineStatus().equals(OnlineStatus.ONLINE)
				&& carEntity.getOnlineStatus().equals(OnlineStatus.ONLINE)) {
			DriverCarEntity driverCarEntity = new DriverCarEntity();
			driverCarEntity.setDriverId(driverId);
			driverCarEntity.setCarId(carId);
			driverCarRepository.save(driverCarEntity);
			carEntity.setOnlineStatus(OnlineStatus.OFFLINE);
			DriverCarDTO driverCarDTO = new DriverCarDTO();
			driverCarDTO.setCarDTO(CarMapper.makeCarDTO(carEntity));
			driverCarDTO.setDriverDTO(DriverMapper.makeDriverDTO(driverEntity));
			return driverCarDTO;
		} else if (null != driverEntity && OnlineStatus.OFFLINE.equals(driverEntity.getOnlineStatus()))
			throw new OfflineDriverException("This Driver is Offline");
		else if (null != carEntity && OnlineStatus.OFFLINE.equals(carEntity.getOnlineStatus()))
			throw new CarAlreadyInUseException("This car is busy now");
		else {
			throw new EntityNotFoundException("Car or Driver entity not found ");
		}
	}

	/**
	 * Unassign a car from a driver
	 *
	 * @param driverId
	 *            & carId
	 */
	@Override
	@Transactional
	public void unassignCarFromDriver(Long driverId, Long carId) throws BusinessException {
		DriverEntity driverEntity;
		CarEntity carEntity;
		DriverCarEntity driverCarEntity;
		driverEntity = driverRepository.findOne(driverId);
		carEntity = carRepository.findOne(carId);
		if (null != driverEntity && null != carEntity && driverEntity.getOnlineStatus().equals(OnlineStatus.ONLINE)) {
			driverCarEntity = driverCarRepository.findByDriverIdAndCarId(driverId, carId);
			if (driverCarEntity == null)
				throw new CarAlreadyInUseException("This car already not assigned to this driver.");
			driverCarRepository.delete(driverCarEntity);
			carEntity.setOnlineStatus(OnlineStatus.ONLINE);
		}
	}

	private DriverEntity findDriverChecked(Long driverId) throws BusinessException {
		DriverEntity driverDO = driverRepository.findOne(driverId);
		if (driverDO == null) {
			throw new EntityNotFoundException("Could not find entity with id: " + driverId);
		}
		return driverDO;
	}

	@Override
	@Transactional
	public List<DriverDTO> findByCarCriterias(CarDTO carDTO) throws BusinessException {
		List<DriverEntity> divers = new ArrayList<>();
		List<CarEntity> cars = (List<CarEntity>) carRepository.findAll();
		AndOrCriteria searchCriteria = new AndOrCriteria(new ConvertibleCriteria(carDTO.getConvertible()),
				new EngineTypeCriteria(carDTO.getEngineType()), new RatingCriteria(carDTO.getRating()),
				new SeatCountCriteria(carDTO.getSeatCount()));
		List<CarEntity> filteredcars = searchCriteria.meets(cars);
		List<Long> carIds = new ArrayList<>();
		Iterator<CarEntity> it = filteredcars.iterator();
		while (it.hasNext()) {
			carIds.add(it.next().getId());

		}
		divers.addAll(driverRepository.findByCarIds(carIds));

		return DriverMapper.makeDriverDTOList(divers);
	}
}
