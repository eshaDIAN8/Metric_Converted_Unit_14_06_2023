package com.example.metricconverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.controller.MetricConverterTddController;
import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.dao.MetricConverterTddDaoImplStatic;
import com.example.metricconverter.service.MetricConverterTddService;

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
	public void testComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillGiveCalculatedResult() throws Exception {
		
	
		
		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=CM&toEnumUnit=CM&value=9";
		
		String result = testRestTemp
				.getForObject(uri, String.class);

		Assert.assertEquals("900.0", result);

	}
	
	
	@Test
	@DisplayName("NEGATIVE TESTING BY PASSING SAME INPUT")
	public void testComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillThrowException() throws Exception {
		

		
		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=CM&toEnumUnit=CM&value=9";
		
		String result = testRestTemp
				.getForObject(uri, String.class);

		Assert.assertEquals("Both input is same which is not allowed", result);

	}

	@Test
	@DisplayName("NEGATIVE TESTING BY PASSING null INPUT")
	public void test1ComponentGivenFromUnitAndToUnit_whenBothInputsAreSame_thenItWillThrowException() throws Exception {
		

		
		String uri = "http://localhost:" + port + "/converter?fromEnumUnit=null&toEnumUnit=CM&value=9";
		
		String result = testRestTemp
				.getForObject(uri, String.class);

		Assert.assertEquals(null, result);

	}


}
