package com.example.metricconverter.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.metricconverter.exception.BothInputRequestParamEqualException;
import com.example.metricconverter.exception.InputMismatchException;
import com.example.metricconverter.inputValidation.MetricConverterTddInputValidation;
import com.example.metricconverter.service.MetricConverterTddService;

@ExtendWith(SpringExtension.class)
// @WebMvcTest(TtdFrontController.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class MetricConverterTddControllerTest {

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
	MetricConverterTddInputValidation inputValidation ;

	

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}


	
	
	
		
	@Test(expected=BothInputRequestParamEqualException.class)
	public void testGivenfromUnitAndtoUnit_whenBothInputsAreSame_thenThrowBothInputRequestParamEqualException()  throws  BothInputRequestParamEqualException{

	
		BothInputRequestParamEqualException e = null;
		  inputValidation.validateInput(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5");
	     ResponseEntity<?> response = controller.getFormulaFromCrud(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5");
		//System.out.println("response::"+response);
	//	Assert.assertEquals(e,  inputValidation.validateInput(MetricConverterTddEnum.METER, MetricConverterTddEnum.METER, "5"));
		

	}
	
	
	
	
	
	
	
	@Test(expected=BothInputRequestParamEqualException.class)
	public void testGivenfromUnitAndtoUnit_whenBothInputsAreNull_thenThrowInputMismatchException() throws  BothInputRequestParamEqualException {
		
		
		
		MetricConverterTddEnum enumFromUnit = MetricConverterTddEnum.METER;
		MetricConverterTddEnum enumToUnit = MetricConverterTddEnum.CM;

		  when(inputValidation.validateInput(enumFromUnit, enumToUnit, "5")).thenReturn(true);
		ResponseEntity<?> response = controller.getFormulaFromCrud(null, null, "5");
		System.out.println("response::"+response.getBody());
		Assert.assertEquals(400, response.getStatusCodeValue());

	}
	
	

	
	
	
	@Test
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingControllerMethod() {

		MetricConverterTddEnum enumFromUnit = MetricConverterTddEnum.METER;
		MetricConverterTddEnum enumToUnit = MetricConverterTddEnum.CM;

		double value = 5;
        when(inputValidation.validateInput(enumFromUnit, enumToUnit, "5")).thenReturn(true);
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);

		ResponseEntity<?> response = controller.getFormulaFromCrud(enumFromUnit, enumToUnit, "5");

		Assert.assertEquals("500.0", response.getBody());

	}
	

	@Test
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingUri_failureScenario() throws Exception  {


		
		double value = 5;
		MetricConverterTddEnum enumFromUnit = MetricConverterTddEnum.METER;
		MetricConverterTddEnum enumToUnit = MetricConverterTddEnum.CM;
		
		  when(inputValidation.validateInput(enumFromUnit, enumToUnit, "5")).thenReturn(true);
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);
		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromEnumUnit", MetricConverterTddEnum.METER.name())
						.param("toEnumUnit", MetricConverterTddEnum.CM.name()).param("value", "5"))
				.andExpect(status().isBadRequest()).andReturn();

		Assert.assertEquals("500.0", mvcResult1.getResponse().getContentAsString());
	}
	
	

	@Test
	public void testGivenEnumfromUnitAndtoUnit_whenBothInputsAreDifferent_thenWeWillGetFormulaFromCrudServiceByHittingUri_successScenario() throws Exception {

		MetricConverterTddEnum enumFromUnit = MetricConverterTddEnum.METER;
		MetricConverterTddEnum enumToUnit = MetricConverterTddEnum.CM;
		double value = 5;

		// String uri = "/converter?fromUnit={fromUnit}&toUnit={toUnit}&value= {value}";
		when(service.getConvertedResult("METER", "CM", value)).thenReturn(500.0);

		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromEnumUnit", MetricConverterTddEnum.METER.toString())
						.param("toEnumUnit", MetricConverterTddEnum.CM.toString()).param("value", "5"))
				.andExpect(status().isOk()).andReturn();

	}

}
