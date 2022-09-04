package rerfb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.config.SwaggerUiConfiguration;
import rerfb.dto.AddressDTO;
import rerfb.service.AddressService;

@Api(tags = { SwaggerUiConfiguration.ADDRESS_CONTROLLER })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {

	@NonNull
	private AddressService addressService;

	@ApiOperation("Return a specific Address by id in the system.")
	@GetMapping("/{id}")
	public AddressDTO getAddressById(@PathVariable Long id) {
		return addressService.getAddressById(id);
	}

	@ApiOperation("Returns list of all Addresses in the system.")
	@GetMapping
	public List<AddressDTO> getAllAddresses() {
		return addressService.getAllAddresses();
	}
}
