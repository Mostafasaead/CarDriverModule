package com.mytaxi.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.TestData;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.entity.DriverEntity;
import com.mytaxi.service.driver.DriverService;

/**
 * @author Mostafa El-Gazzar.
 */
public class DriverControllerTest extends TestData {
	private static final ObjectMapper mapper = new ObjectMapper();

	private MockMvc mvc;

	@Mock
	private DriverService driverService;

	@InjectMocks
	private DriverController driverController;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(DriverController.class);
	}

	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(driverController).dispatchOptions(true).build();
	}

	@Test
	public void testAssignCarToDriver() throws Exception {
		DriverCarDTO driverCarDTO = getDriverCarDTO();
		doReturn(driverCarDTO).when(driverService).assignCarToDriver(any(Long.class), any(Long.class));
		driverController.assignCarToDriver(7L, 2L);
		MvcResult result = mvc.perform(post("/v1/drivers/7/selectCar").param("carId", "2"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("admin"));

	}

	@Test
	public void testUnAssignCarFromDriver() throws Exception {
		doNothing().when(driverService).unassignCarFromDriver(any(Long.class), any(Long.class));
		driverController.unassignCarFromDriver(1L, 1L);
		MvcResult result = mvc.perform(delete("/v1/drivers/7/deselectCar").param("carId", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testGetDriver() throws Exception {
		DriverEntity driverEntity = DriverMapper.makeDriverDO((getDriverDTO()));
		doReturn(driverEntity).when(driverService).find(any(Long.class));
		driverController.getDriver(1L);
		MvcResult result = mvc.perform(get("/v1/drivers/{driverId}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("admin"));
	}

	@Test
	public void testUpdateLocation() throws Exception {
		doNothing().when(driverService).updateLocation(any(Long.class), any(Double.class), any(Double.class));
		driverController.updateLocation(1L, 99, 99);
		MvcResult result = mvc
				.perform(put("/v1/drivers/{driverId}", 1).param("longitude", "99").param("latitude", "99"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testCreateDriver() throws Exception {
		DriverDTO driverDTO = getDriverDTO();
		DriverEntity driverEntity = DriverMapper.makeDriverDO((getDriverDTO()));
		String jsonInString = mapper.writeValueAsString(driverDTO);
		doReturn(driverEntity).when(driverService).create(any(DriverEntity.class));
		driverController.createDriver(driverDTO);
		MvcResult result = mvc
				.perform(post("/v1/drivers").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonInString))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("admin"));

	}

	@Test
	public void testFindDriverByCarAttributes() throws Exception {
		List<DriverDTO> driverData = Collections.singletonList(getDriverDTO());
		doReturn(driverData).when(driverService).findByCarCriterias(any(CarDTO.class));
		CarDTO carDTO = new CarDTO();
		carDTO.setSeatCount(4);
		carDTO.setEngineType("gas");
		String jsonInString = mapper.writeValueAsString(carDTO);
		driverController.findByCarSpecs(carDTO);
		MvcResult result = mvc.perform(
				post("/v1/drivers/findByCarSpecs").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonInString))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("admin"));
	}

	@Test
	public void testDeleteDriver() throws Exception {
		doNothing().when(driverService).delete(any(Long.class));
		driverController.deleteDriver(1L);
		MvcResult result = mvc.perform(delete("/v1/drivers/{driverId}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testFindByOnlineStatus() throws Exception {
		List<DriverEntity> driverEntity = Collections.singletonList(DriverMapper.makeDriverDO((getDriverDTO())));
		doReturn(driverEntity).when(driverService).find(any(OnlineStatus.class));
		driverController.findDrivers(OnlineStatus.ONLINE);
		MvcResult result = mvc.perform(get("/v1/drivers").param("onlineStatus", OnlineStatus.ONLINE.toString()))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();
		Assert.assertTrue(responseBody.contains("admin"));
	}

}
