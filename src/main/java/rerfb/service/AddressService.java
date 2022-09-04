package rerfb.service;

import java.util.List;

import rerfb.dto.AddressDTO;

public interface AddressService {

	AddressDTO getAddressById(Long id);

	List<AddressDTO> getAllAddresses();

}
