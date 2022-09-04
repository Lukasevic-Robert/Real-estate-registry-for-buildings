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
import rerfb.dto.OwnerDTO;
import rerfb.exceptions.ApiError;
import rerfb.service.OwnerService;

@Api(tags = { SwaggerUiConfiguration.OWNER_CONTROLLER })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

	@NonNull
	private OwnerService ownerService;

	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "COULD NOT PROCESS", response = ApiError.class),
			@ApiResponse(code = 500, message = "SERVER ERROR", response = ApiError.class) 
			})
	@ApiOperation("Creates or Updates existing Owner.")
	@PostMapping
	public OwnerDTO createUpdateOwner(@RequestBody OwnerDTO ownerDTO) {
		return ownerService.createUpdateOwner(ownerDTO);
	}

	@ApiOperation("Return a specific Owner by id in the system.")
	@GetMapping("/{id}")
	public OwnerDTO getOwnerById(@PathVariable Long id) {
		return ownerService.getOwnerById(id);
	}

	@ApiOperation("Deletes Owner by id from the system.")
	@DeleteMapping("/{id}")
	public void deleteOwnerById(@PathVariable Long id) {
		ownerService.deleteById(id);
	}

	@ApiOperation("Returns list of all Owners in the system.")
	@GetMapping
	public List<OwnerDTO> getAllOwners() {
		return ownerService.getAllOwners();
	}
}
