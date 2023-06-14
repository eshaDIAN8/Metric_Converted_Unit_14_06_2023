package com.example.metricconverter.dao;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MetricConverterTddDaoImplTest {

	@InjectMocks
	MetricConverterTddDao dao = new MetricConverterTddDaoImpl();

	private RestTemplate restTmplate ;

	

	

	@Test
	public void testIntegrationGivenFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetFormulaDirectlyFromdaoImpl() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		
		MetricConverterTddDaoImpl daoImpl = new MetricConverterTddDaoImpl();
		Class obj = daoImpl.getClass();
		 Field field = obj.getDeclaredField("restTmp");
		 field.setAccessible(true);
		 field.set(daoImpl, new RestTemplate());
	
		String fromUnit = "METER";
		String toUnit = "CM";
		
		String formula = daoImpl.getFormula(fromUnit, toUnit);
				Assert.assertEquals("*100", formula);

		
	}

}
