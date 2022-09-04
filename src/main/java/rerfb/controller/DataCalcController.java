package rerfb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.config.SwaggerUiConfiguration;
import rerfb.service.DataCalcService;

@Api(tags = { SwaggerUiConfiguration.DATA_CALC_CONTROLLER })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calc")
public class DataCalcController {

	@NonNull
	private DataCalcService dataCalcService;

	@GetMapping("/tax/yearly-real-estate/{ownerId}")
	@ApiOperation("Calculate the total yearly real estate tax (market value times tax rate) for\r\n"
			+ "all properties owned by a particular owner.")
	public Map<String, Object> getYearlyRealEstateTax(@PathVariable Long ownerId) {
		Map<String, Object> output = new HashMap<>();
		output.put("taxes for owner with id: " + String.valueOf(ownerId),
				dataCalcService.getYearlyRealEstateTax(ownerId));
		return output;
	}
}
