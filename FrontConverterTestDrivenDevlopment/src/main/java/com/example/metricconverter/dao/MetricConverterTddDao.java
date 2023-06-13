package com.example.metricconverter.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MetricConverterTddDao {
	
 

public String getFormula(String fromUnit, String toUnit) ;

}
