package rerfb.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.dto.OwnerDTO;
import rerfb.exceptions.NotFoundException;
import rerfb.exceptions.UnprocessableEntityException;
import rerfb.model.OwnerJPA;
import rerfb.repository.OwnerRepository;
import rerfb.service.OwnerService;
import rerfb.utils.GenericBuilder;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

	@NonNull
	private OwnerRepository ownerRepository;

	@Override
	public void deleteById(Long id) {
		if (!ownerRepository.existsById(id))
			throw new NotFoundException("Owner with id: " + id + " does not exist.");
		ownerRepository.deleteById(id);
	}

	public OwnerDTO getOwnerById(Long id) {
		return ownerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Owner with id: " + id + " not found.")).createDTO();
	}

	@Override
	public OwnerDTO createUpdateOwner(OwnerDTO owner) {
		if (owner == null)
			throw new UnprocessableEntityException("Could not create Owner, bad credentials.");
		if (owner.getId() == null) {
			return ownerRepository.save(ownerDTO_to_ownerJPA.apply(owner)).createDTO();
		} else {
			return updateOwner(owner);
		}
	}

	private OwnerDTO updateOwner(OwnerDTO owner) {
		OwnerJPA updated = ownerRepository.findById(owner.getId())
				.orElseThrow(() -> new NotFoundException("Owner with id: " + owner.getId() + " not found."));
		updated.doUpdate(owner);
		return ownerRepository.save(updated).createDTO();
	}

	Function<OwnerDTO, OwnerJPA> ownerDTO_to_ownerJPA = o -> 
		GenericBuilder.of(OwnerJPA::new)
			.with(OwnerJPA::setFirstName, o.getFirstName())
			.with(OwnerJPA::setLastName, o.getLastName())
			.with(OwnerJPA::setEmail, o.getEmail())
			.build();

	@Override
	public List<OwnerDTO> getAllOwners() {
		return ownerRepository.findAll().stream().map(OwnerJPA::createDTO).collect(Collectors.toList());
	}

}
