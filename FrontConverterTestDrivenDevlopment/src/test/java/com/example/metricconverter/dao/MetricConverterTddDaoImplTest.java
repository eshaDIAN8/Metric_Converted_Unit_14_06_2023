package com.example.metricconverter.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.exception.ConnectionRefusedException;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// (webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class MetricConverterTddDaoImplTest {

	@InjectMocks
	MetricConverterTddDao dao = new MetricConverterTddDaoImpl();
	/*
	 * @Value(value = "${local.server.port}") private int port;
	 */

	//@Autowired
	private RestTemplate restTmp ;

	// hw to access private instace variable outside of the class by using
	// reflection

@BeforeAll
public void creatingObjectByUsingReflection() {
	MetricConverterTddDaoImpl b = new MetricConverterTddDaoImpl();
	b.setRestTmp(restTmp);
//	Field field = MetricConverterTddDaoImpl.class.getDeclaredField(restTmp);
   // field.setAccessible(true);
   // Object value = field.get(t);
   // System.out.println(value);
	
}
	

	@Test
	public void testIntegrationGivenFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetFormulaDirectlyFromdaoImpl() {

		String fromUnit = "METER";
		String toUnit = "CM";
		
		String formula = dao.getFormula(fromUnit, toUnit);

		System.out.println("getting formula from crud service::" + formula);
		// assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.XXXX);
		// assertThat(formula.getStatusCode()).isEqualTo(HttpStatus.ok);
		Assert.assertEquals("*100", formula);

	}

}
