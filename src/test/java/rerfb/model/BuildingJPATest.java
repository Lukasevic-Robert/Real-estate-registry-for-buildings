package rerfb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.dto.BuildingDTO;
import rerfb.testUtils.BuildingUtils;

@SpringBootTest
public class BuildingJPATest {

	@Test
	public void doUpdate_test() {
		BuildingJPA buildingJPA = new BuildingJPA();
		BuildingDTO buildingDTO = BuildingUtils.createBuildingDTO();
		buildingJPA.doUpdate(buildingDTO);
		assertEquals(buildingDTO.getType(), buildingJPA.getType().name());
	}

	@Test
	public void createDTO_test() {
		BuildingJPA buildingJPA = BuildingUtils.createBuilding(999L, new OwnerJPA());
		BuildingDTO buildingDTO = buildingJPA.createDTO();
		assertEquals(buildingJPA.getType().name(), buildingDTO.getType());
	}
}
