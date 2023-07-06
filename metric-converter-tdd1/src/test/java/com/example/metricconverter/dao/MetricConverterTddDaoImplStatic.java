package com.example.metricconverter.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test"})
public class MetricConverterTddDaoImplStatic  implements MetricConverterTddDao{

	
	
	
	
	

	@Override
	public String getFormula(String fromUnit, String toUnit) {
		System.out.println("inside daoimpl static java file");
		
		String convertedUnit = fromUnit+"-"+toUnit;
		Map<String,String> map = new HashMap<>();
		map.put("KM-METER","*1000");
		map.put("METER-KM", "/1000");
		map.put("INCH-METER","*0.025");
		map.put("METER-INCH","/0.025");
		map.put("MM-METER","/1000");
		map.put("METER-MM","*1000");
		map.put("CM-METER","/100");
		map.put("METER-CM","*100");
		
		
			return map.get(convertedUnit);
		
	}

}
