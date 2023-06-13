package com.example.metricconverter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.exception.BothInputRequestParamEqualException;
import com.example.metricconverter.exception.InputMismatchException;
import com.example.metricconverter.inputValidation.MetricConverterTddInputValidation;
import com.example.metricconverter.service.MetricConverterTddService;

@RestController
@Validated
public class MetricConverterTddController {

	private static Logger logger = LogManager.getLogger(MetricConverterTddController.class);

	@Autowired
	private MetricConverterTddService service;

	@Enumerated(EnumType.STRING)
	private MetricConverterTddEnum unit;

	@Autowired
	MetricConverterTddInputValidation inputValidation ;

	String fromUnit;
	String toUnit;
	String finalConvertedResult;

	@GetMapping("/converter")
	public ResponseEntity<?> getFormulaFromCrud(@RequestParam MetricConverterTddEnum fromEnumUnit,
			@RequestParam MetricConverterTddEnum toEnumUnit, @RequestParam String value)
			throws BothInputRequestParamEqualException {
		Boolean validatedResult = false ;
		
         
		validatedResult =  inputValidation.validateInput(fromEnumUnit, toEnumUnit, value);
      

		double unitToBeConvertedResult = 0;
   
		
		if(validatedResult) {
			
			double dValue = Double.parseDouble(value);

			unitToBeConvertedResult = service.getConvertedResult(fromEnumUnit.name(), toEnumUnit.name(), dValue);
			logger.info("inside metric converted tdd controller " + unitToBeConvertedResult);

			finalConvertedResult = String.valueOf(unitToBeConvertedResult);
			logger.info(finalConvertedResult);
		}
		
			//return  new ResponseEntity(new BothInputRequestParamEqualException("validation failed"),HttpStatus.BAD_REQUEST);
			logger.info("input parameter validation issue");
					

		return new ResponseEntity<>(finalConvertedResult,HttpStatus.OK);

	}

}
