package rerfb.service.impl;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rerfb.exceptions.NotFoundException;
import rerfb.repository.BuildingRepository;
import rerfb.repository.OwnerRepository;
import rerfb.service.DataCalcService;

@RequiredArgsConstructor
@Service
public class DataCalcServiceImpl implements DataCalcService {

	@NonNull
	private BuildingRepository buildingRepository;
	@NonNull
	private OwnerRepository ownerRepository;

	@Override
	public double getYearlyRealEstateTax(Long ownerId) {
		if (!ownerRepository.existsById(ownerId))
			throw new NotFoundException("Owner with id: " + ownerId + " not found.");
		return buildingRepository.getBuildingsByOwnerId(ownerId).stream()
				.mapToDouble(b -> b.getMarketValue() * b.getType().getTaxRate() / 100).sum();
	}
}
