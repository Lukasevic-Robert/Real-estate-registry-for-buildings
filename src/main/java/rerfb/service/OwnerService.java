package rerfb.service;

import java.util.List;

import rerfb.dto.OwnerDTO;

public interface OwnerService {

	OwnerDTO getOwnerById(Long id);

	OwnerDTO createUpdateOwner(OwnerDTO owner);

	List<OwnerDTO> getAllOwners();

	void deleteById(Long id);
}
