package rerfb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.dto.AddressDTO;
import rerfb.exceptions.NotFoundException;
import rerfb.model.AddressJPA;
import rerfb.repository.AddressRepository;
import rerfb.service.AddressService;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

	@NonNull
	private AddressRepository addressRepository;

	@Override
	public AddressDTO getAddressById(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Address with id: " + id + " not found.")).createDTO();
	}

	@Override
	public List<AddressDTO> getAllAddresses() {
		return addressRepository.findAll().stream().map(AddressJPA::createDTO).collect(Collectors.toList());
	}

}
