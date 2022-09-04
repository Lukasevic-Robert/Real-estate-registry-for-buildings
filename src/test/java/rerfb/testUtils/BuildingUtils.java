package rerfb.testUtils;

import java.util.ArrayList;
import java.util.List;

import rerfb.dto.BuildingDTO;
import rerfb.enums.PropertyType;
import rerfb.model.AddressJPA;
import rerfb.model.BuildingJPA;
import rerfb.model.OwnerJPA;

public class BuildingUtils {

	public static BuildingJPA createBuilding(Long id, OwnerJPA owner) {
		BuildingJPA buildingJPA = new BuildingJPA();
		buildingJPA.setId(id);
		buildingJPA.setSize(999);
		buildingJPA.setType(PropertyType.APARTMENT);
		buildingJPA.setMarketValue(10000);
		buildingJPA.setAddress(new AddressJPA("Vilnius", "Cvirkos g.", "10"));
		if(owner.getId() != null) {
			buildingJPA.setOwner(owner);
		}
		return buildingJPA;
	}

	public static List<BuildingJPA> createBuildingList(int length, OwnerJPA owner) {
		List<BuildingJPA> buildings = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			buildings.add(createBuilding(Long.valueOf(8888 + i), owner));
		}
		return buildings;
	}
	
	public static BuildingDTO createBuildingDTO() {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setSize(999);
		buildingDTO.setType(PropertyType.APARTMENT.name());
		buildingDTO.setMarketValue(10000);
		return buildingDTO;
	}

}
