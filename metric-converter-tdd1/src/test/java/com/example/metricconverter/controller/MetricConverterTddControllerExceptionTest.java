package com.example.metricconverter.controller;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.exception.BothInputRequestParamEqualException;
import com.example.metricconverter.inputValidation.MetricConverterTddInputValidation;
import com.example.metricconverter.service.MetricConverterTddService;

@ExtendWith(SpringExtension.class)

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class MetricConverterTddControllerExceptionTest {

	@InjectMocks
	private MetricConverterTddController controller;

	@Mock
	private MetricConverterTddService service;

	private MockMvc mockMvc;

	@Mock
	private MockMvcRequestBuilders mockMvcBuilder;

	@Mock
	MetricConverterTddDao dao;

	private MetricConverterTddInputValidation validation;

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test(expected = BothInputRequestParamEqualException.class)
	public void testGivenfromUnitAndtoUnit_whenBothInputsAreSame_thenThrowBothInputRequestParamEqualException()
			throws BothInputRequestParamEqualException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {

		MetricConverterTddController contrlr = new MetricConverterTddController();
		Class obj = contrlr.getClass();
		Field field = obj.getDeclaredField("inputValidation");
		field.setAccessible(true);
		field.set(contrlr, new MetricConverterTddInputValidation());

		ResponseEntity<?> response = null;
		BothInputRequestParamEqualException e = null;

		response = contrlr.getFormulaFromCrud(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5");

		System.out.println("response::" + response);

		Assertions.assertThrows(IllegalArgumentException.class, () -> field.get(contrlr));

	}

	@Test(expected = BothInputRequestParamEqualException.class)
	public void testGivenfromUnitAndtoUnit_whenBothInputsAreNull_thenThrowInputMismatchException()
			throws BothInputRequestParamEqualException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		MetricConverterTddController contrlr = new MetricConverterTddController();
		Class obj = contrlr.getClass();
		Field field = obj.getDeclaredField("inputValidation");
		field.setAccessible(true);
		field.set(contrlr, new MetricConverterTddInputValidation());
		ResponseEntity<?> response = contrlr.getFormulaFromCrud(MetricConverterTddEnum.METER,
				MetricConverterTddEnum.NULL, "5");
		System.out.println("response::" + response.getBody());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCodeValue());

	}

}
