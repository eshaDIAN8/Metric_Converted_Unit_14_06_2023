package com.example.metricconverter.inputValidation;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.exception.BothInputRequestParamEqualException;

@Component
public class MetricConverterTddInputValidation {

	public Boolean validateInput(MetricConverterTddEnum fromEnumUnit, MetricConverterTddEnum toEnumUnit,
			@NotNull @NotEmpty String value) throws BothInputRequestParamEqualException {


		String fromUnit = null;
		String toUnit = null;
		Boolean b = false;

		fromUnit = fromEnumUnit.name();
		System.out.println("fromUnit::" + fromUnit);
		toUnit = toEnumUnit.name();
		System.out.println("toUnit::" + fromUnit);

		if (fromUnit.equalsIgnoreCase(toUnit) || StringUtils.isEmpty(fromUnit) || StringUtils.isEmpty(toUnit)) {
			b = false;

			throw new BothInputRequestParamEqualException("both input shd not be same");

		} else {
			b = true;
			System.out.println("inputs are different ");
		}

		return b;
	}
}
