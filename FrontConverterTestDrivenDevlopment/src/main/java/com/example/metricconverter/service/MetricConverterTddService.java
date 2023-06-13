package com.example.metricconverter.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.exception.FormulaNotFoundException;
import com.example.metricconverter.exception.InputMismatchException;


@Service
public class MetricConverterTddService {

	private static Logger logger = LogManager.getLogger(MetricConverterTddService.class);

	@Autowired
	private RestTemplate restTmp;

	@Autowired
	MetricConverterTddDao dao;

	public Double getConvertedResult(String fromUnit, String toUnit, double value) throws InputMismatchException{

		double unitToBeConvertedResult = 0;

		String formula = null;

		
        
        	   
			formula = dao.getFormula(fromUnit, toUnit);
			logger.info(" inside getConvertedResult() formula ::" + formula);

		
			unitToBeConvertedResult = getAfterProcessingResult(formula, value);
			logger.info("convertedresult:" + unitToBeConvertedResult);

			
			
		

		return unitToBeConvertedResult;
	}

	private Double getAfterProcessingResult(String formula, double value) {
		double doubleValue;

		double processedResult;

		processedResult = (formula.charAt(0) == '*') ? value * Double.parseDouble(formula.replace("*", ""))
				: value / Double.parseDouble(formula.replace("/", ""));

		logger.info(" inside service  processing result::" + processedResult);

		return processedResult;

	}

}
