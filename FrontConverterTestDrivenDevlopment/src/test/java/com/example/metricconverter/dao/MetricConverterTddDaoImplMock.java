package com.example.metricconverter.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.exception.ConnectionRefusedException;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MetricConverterTddDaoImplMock {

	@InjectMocks
	MetricConverterTddDao dao = new MetricConverterTddDaoImpl();
	

	@Mock
	private RestTemplate restTmp;

	@Test
	public void testIntegrationGivenFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetFormulaDirectlyFromdaoImplby_mockingCrudServcCall() {

		String fromUnit = "METER";
		String toUnit = "CM";

		String uri = "http://localhost:8080/getConvertedUnit/crud?fromUnit=" + fromUnit + "&toUnit=" + toUnit;
		Mockito.when(restTmp.getForObject(uri, String.class)).thenReturn("*100");
		String formula = dao.getFormula(fromUnit, toUnit);
		System.out.println("formula::" + formula);

		Assert.assertEquals("*100", formula);

	}

	
	
	@Test(expected = ConnectionRefusedException.class)
	@DisplayName("testing negative scenario by mocking rest template call")
	public void test1IntegrationGivenFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetFormulaDirectlyFromdaoImplby_mockingCrudServcCall()
			throws ConnectionRefusedException {

		String fromUnit = "METER";
		String toUnit = "CM";

		String uri = "http://localhost:808/getConvertedUnit/crud?fromUnit=" + fromUnit + "&toUnit=" + toUnit;
		Mockito.when(restTmp.getForObject(uri, String.class)).thenReturn(null);
		String formula = dao.getFormula(fromUnit, toUnit);
		System.out.println("formula::" + formula);

		Assert.assertEquals("not able to connect with crud service due to some issue", formula);

	}

}
