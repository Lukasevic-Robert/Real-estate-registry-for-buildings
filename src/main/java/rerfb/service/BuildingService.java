package rerfb.service;

import java.util.List;

import rerfb.dto.BuildingDTO;

public interface BuildingService {

	List<BuildingDTO> getBuildingsByOwnerId(Long id);

	List<BuildingDTO> getAllBuildings();

	void deleteById(Long id);

	BuildingDTO createUpdateBuilding(BuildingDTO building);

	BuildingDTO getBuildingById(Long id);

}
