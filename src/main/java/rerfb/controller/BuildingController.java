package rerfb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.config.SwaggerUiConfiguration;
import rerfb.dto.BuildingDTO;
import rerfb.exceptions.ApiError;
import rerfb.service.BuildingService;

@Api(tags = { SwaggerUiConfiguration.BUILDING_CONTROLLER })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/building")
public class BuildingController {

	@NonNull
	private BuildingService buildingService;

	@ApiResponses(value = { 
			@ApiResponse(code = 422, message = "COULD NOT PROCESS", response = ApiError.class),
			@ApiResponse(code = 500, message = "SERVER ERROR", response = ApiError.class) 
			})
	@ApiOperation("Creates or Updates existing Building. Address is created together with the building.")
	@PostMapping
	public BuildingDTO createUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
		return buildingService.createUpdateBuilding(buildingDTO);
	}

	@ApiOperation("Return a specific Building by id in the system.")
	@GetMapping("/{id}")
	public BuildingDTO getBuildingById(@PathVariable Long id) {
		return buildingService.getBuildingById(id);
	}

	@ApiOperation("Deletes Building by id from the system.")
	@DeleteMapping("/{id}")
	public void deleteBuildingById(@PathVariable Long id) {
		buildingService.deleteById(id);
	}

	@ApiOperation("Returns list of all Buildings in the system.")
	@GetMapping
	public List<BuildingDTO> getAllBuildings() {
		return buildingService.getAllBuildings();
	}
}
