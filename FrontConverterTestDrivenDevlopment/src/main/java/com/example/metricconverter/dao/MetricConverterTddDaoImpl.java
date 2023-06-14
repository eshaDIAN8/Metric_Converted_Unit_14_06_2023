package com.example.metricconverter.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.exception.ConnectionRefusedException;

@Component
@Profile({ "!test" })
public class MetricConverterTddDaoImpl implements MetricConverterTddDao {

	private static Logger logger = LogManager.getLogger(MetricConverterTddDaoImpl.class);

	@Autowired
	private RestTemplate restTmp;

	private String formula;

	public String getFormula(String fromUnit, String toUnit) {

		String uri = "http://localhost:8080/getConvertedUnit/crud?fromUnit=" + fromUnit + "&toUnit=" + toUnit;
		System.out.println("uri:" + uri);
		formula = restTmp.getForObject(uri, String.class);

		logger.info("formula from crud MS:" + formula);

		if (StringUtils.isEmpty(formula)) {

			logger.error(" not able to fetch formula from crud:" + formula);
			throw new ConnectionRefusedException("not able to connect with crud service due to port issue::");
		} else {

			logger.info("formula in else service:" + formula);
		}

		return formula;
	}

	
}
