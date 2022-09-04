package rerfb.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.dto.AddressDTO;
import rerfb.dto.BuildingDTO;
import rerfb.enums.PropertyType;
import rerfb.exceptions.NotFoundException;
import rerfb.exceptions.UnprocessableEntityException;
import rerfb.model.AddressJPA;
import rerfb.model.BuildingJPA;
import rerfb.repository.BuildingRepository;
import rerfb.repository.OwnerRepository;
import rerfb.service.BuildingService;
import rerfb.utils.GenericBuilder;

@RequiredArgsConstructor
@Service
public class BuildingServiceImpl implements BuildingService {

	@NonNull
	private BuildingRepository buildingRepository;
	@NonNull
	private OwnerRepository ownerRepository;

	@Override
	public void deleteById(Long id) {
		if (!buildingRepository.existsById(id))
			throw new NotFoundException("Building with id: " + id + " does not exist.");
		buildingRepository.deleteById(id);
	}

	@Override
	public BuildingDTO createUpdateBuilding(BuildingDTO building) {
		if (building == null)
			throw new UnprocessableEntityException("Could not create Building, bad credentials.");
		if (building.getId() == null) {
			BuildingJPA buildingJPA = buildingDTO_to_buildingJPA.apply(building);
			if (building.getAddress() != null) {
				buildingJPA.setAddress(createAddress(building.getAddress()));
			}
			return buildingRepository.save(buildingJPA).createDTO();
		} else {
			return updateBuilding(building);
		}
	}

	private BuildingDTO updateBuilding(BuildingDTO building) {
		BuildingJPA updated = getBuildingJPAById(building.getId());
		updated.doUpdate(building);
		if (building.getAddress() != null && building.getAddress().getId() == null) {
			updated.setAddress(createAddress(building.getAddress()));
		}
		if (building.getOwnerId() != null)
			updated.setOwner(ownerRepository.findById(building.getOwnerId())
					.orElseThrow(() -> new NotFoundException("Owner with id: " + building.getOwnerId() + " not found.")));
		return buildingRepository.save(updated).createDTO();
	}
	
	private AddressJPA createAddress(AddressDTO addressDTO) {
		AddressJPA addressJPA = new AddressJPA();
		addressJPA.doUpdate(addressDTO);
		return addressJPA;
	}

	private BuildingJPA getBuildingJPAById(Long id) {
		return buildingRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Building with id: " + id + " not found."));
	}

	@Override
	public BuildingDTO getBuildingById(Long id) {
		return buildingRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Building with id: " + id + " not found.")).createDTO();
	}

	@Override
	public List<BuildingDTO> getBuildingsByOwnerId(Long id) {
		return buildingRepository.getBuildingsByOwnerId(id).stream().map(BuildingJPA::createDTO)
				.collect(Collectors.toList());
	}

	Function<BuildingDTO, BuildingJPA> buildingDTO_to_buildingJPA = b -> 
		GenericBuilder.of(BuildingJPA::new)
			.with(BuildingJPA::setOwner,
						b.getOwnerId() != null ? ownerRepository.findById(b.getOwnerId()).orElseThrow(
					() -> new NotFoundException("Owner with id: " + b.getOwnerId() + " not found.")) : null)
			.with(BuildingJPA::setSize, b.getSize()).with(BuildingJPA::setMarketValue, b.getMarketValue())
			.with(BuildingJPA::setType, PropertyType.valueOf(b.getType()))
			.build();

	@Override
	public List<BuildingDTO> getAllBuildings() {
		return buildingRepository.findAll().stream().map(BuildingJPA::createDTO).collect(Collectors.toList());
	}
}
