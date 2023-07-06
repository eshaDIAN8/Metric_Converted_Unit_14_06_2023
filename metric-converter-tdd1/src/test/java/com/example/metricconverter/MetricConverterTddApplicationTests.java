package com.example.metricconverter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.metricconverter.dao.MetricConverterTddDao;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles({ "test" })
class MetricConverterTddApplicationTests {

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	MetricConverterTddDao dao;

	@Autowired
	private TestRestTemplate testRestTemp;

	@Test
	void contextLoads() {

	}
	
	
	
	@Test
	public void testMain() {
		
MetricConverterTddApplication.main(new String[] {});
	assertTrue(true);
	}

	@Test
	public void testComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillGiveCalculatedResult()
			throws Exception {

		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=CM&toEnumUnit=CM&value=9";

		String result = testRestTemp.getForObject(uri, String.class);

		Assert.assertEquals("900.0", result);

	}

	@Test
	@DisplayName("NEGATIVE TESTING BY PASSING SAME INPUT")
	public void testComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillThrowException() throws Exception {

		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=CM&toEnumUnit=CM&value=9";

		String result = testRestTemp.getForObject(uri, String.class);

		Assert.assertEquals("Both input is same which is not allowed", result);

	}

	@Test
	@DisplayName("NEGATIVE TESTING BY PASSING null INPUT")
	public void test1ComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillThrowException() throws Exception {

		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=null&toEnumUnit=CM&value=9";

		String result = testRestTemp.getForObject(uri, String.class);

		Assert.assertEquals(null, result);

	}

}
