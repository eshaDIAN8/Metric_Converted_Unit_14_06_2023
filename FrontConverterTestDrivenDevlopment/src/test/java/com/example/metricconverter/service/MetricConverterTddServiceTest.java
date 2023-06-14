package com.example.metricconverter.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.metricconverter.dao.MetricConverterTddDao;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MetricConverterTddServiceTest {

	@InjectMocks
	MetricConverterTddService service;

	@Mock
	private TestTemplate testRestTemplate;

	@Mock
	MetricConverterTddDao dao;

	@Test
	public void testGivenFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetMetricConvertedResult() {

		String fromUnit = "METER";
		String toUnit = "CM";

		when(dao.getFormula("METER", "CM")).thenReturn("*100");
		double convertedResult = service.getConvertedResult(fromUnit, toUnit, 5);

		String convertedResult1 = String.valueOf(convertedResult);

		Assert.assertEquals("500.0", convertedResult1);

	}

	@Test
	public void testGivenFromUnitAndToUnitReverseOrder_whenBothInputsAreDifferent_thenWillGetMetricConvertedResult() {

		String fromUnit = "CM";
		String toUnit = "Meter";

		when(dao.getFormula("CM", "METER")).thenReturn("/100");

		double convertedResultt = service.getConvertedResult("CM", "METER", 5);

		String convertedResult2 = String.valueOf(convertedResultt);

		Assert.assertEquals("0.05", convertedResult2);

	}


	

}
