package com.mytaxi.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mytaxi.MytaxiServerApplicantTestApplication;

/**
 * @author Mostafa El-Gazzar.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = MytaxiServerApplicantTestApplication.class)
public class RepositoryTest {
	@Test
	public void mainTest() {
	}
}
