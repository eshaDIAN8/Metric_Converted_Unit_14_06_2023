package com.example.metricconverter.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.inputValidation.MetricConverterTddInputValidation;
import com.example.metricconverter.service.MetricConverterTddService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class MetricConverterTddControllerMockTest {
	
	

	@InjectMocks
	private MetricConverterTddController controller;

	@Mock
	private MetricConverterTddService service;

	
   private MockMvc mockMvc;

	@Mock
	private MockMvcRequestBuilders mockMvcBuilder;

	@Mock
	MetricConverterTddDao dao;
	
	@Mock
	private MetricConverterTddInputValidation validation;
	
	

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}


	
	
	
	@Test
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingControllerMethod() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		
			 
		MetricConverterTddEnum enumFromUnit = MetricConverterTddEnum.METER;
		MetricConverterTddEnum enumToUnit = MetricConverterTddEnum.CM;
		

		double value = 5;
       when(validation.validateInput(enumFromUnit, enumToUnit, "5")).thenReturn(true);
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);

		ResponseEntity<?> response = controller.getFormulaFromCrud(enumFromUnit, enumToUnit, "5");

		Assert.assertEquals("500.0", response.getBody());

	}
	
	@Test
	@DisplayName("where validation response false")
	public void test1GivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingControllerMethod() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		
	

		double value = 5;
       when(validation.validateInput(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5")).thenReturn(false);

		ResponseEntity<?> response = controller.getFormulaFromCrud(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5");

		Assert.assertEquals("400 BAD_REQUEST", response.getStatusCode());

	}
	
	

	@Test
	@DisplayName("success scenario ")
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingUri_successScenario() throws Exception  {
		
		
		double value = 5;
		
		
		when(validation.validateInput(MetricConverterTddEnum.METER, MetricConverterTddEnum.CM, "5")).thenReturn(true);
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);
		
		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromEnumUnit", MetricConverterTddEnum.METER.name())
						.param("toEnumUnit", MetricConverterTddEnum.CM.name()).param("value", "5"))
				.andExpect(status().isOk()).andReturn();

		Assert.assertEquals("500.0", mvcResult1.getResponse().getContentAsString());
	}
	
	
	

	@Test
	@DisplayName("failure scenario")
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingUri_failureScenario() throws Exception {
		
		double value = 5;
				
		when(validation.validateInput(MetricConverterTddEnum.METER, MetricConverterTddEnum.CM, "5")).thenReturn(true);
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);
		
		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromEnumUnit", MetricConverterTddEnum.METER.name())
						.param("toEnumUnit", MetricConverterTddEnum.CM.name()).param("value", "5"))
				.andExpect(status().isBadRequest()).andReturn();
		System.out.println("mvcResult1::"+mvcResult1.getResponse().getContentAsString());
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, mvcResult1.getResponse().getContentAsString());

	}


}
