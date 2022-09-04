package rerfb.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import rerfb.dto.AddressDTO;
import rerfb.dto.BuildingDTO;
import rerfb.exceptions.NotFoundException;
import rerfb.exceptions.UnprocessableEntityException;
import rerfb.model.AddressJPA;
import rerfb.model.BuildingJPA;
import rerfb.model.OwnerJPA;
import rerfb.repository.BuildingRepository;
import rerfb.repository.OwnerRepository;
import rerfb.testUtils.AddressUtils;
import rerfb.testUtils.BuildingUtils;
import rerfb.testUtils.OwnerUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingServiceImplTest {

	@Autowired
	BuildingRepository buildingRepository;
	@Autowired
	BuildingService buildingService;
	@Autowired
	OwnerRepository ownerRepository;

	@Test
	public void getBuildingById_test() {
		BuildingDTO building1 = buildingRepository.save(BuildingUtils.createBuilding(9999L, new OwnerJPA()))
				.createDTO();
		BuildingDTO building2 = buildingService.getBuildingById(building1.getId());
		assertThat(building1).usingRecursiveComparison().isEqualTo(building2);
	}

	@Test
	public void getBuildingById_ThrowsNotFoundException_test() {
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			buildingService.getBuildingById(9090909090L);
		});
		assertEquals("Building with id: " + 9090909090L + " not found.", notFoundException.getMessage());
	}

	@Test
	public void deleteBuildingById_test() {
		Long id = buildingRepository.save(BuildingUtils.createBuilding(9999L, new OwnerJPA())).getId();
		buildingService.deleteById(id);
		assertNull(buildingRepository.findById(id).orElse(null));
	}

	@Test
	public void deleteBuildingById_ThrowsNotFoundException_test() {
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			buildingService.deleteById(9090909090L);
		});
		assertEquals("Building with id: " + 9090909090L + " does not exist.", notFoundException.getMessage());
	}

	@Test
	public void createBuilding_test() {
		BuildingDTO requestDTO = BuildingUtils.createBuildingDTO();
		BuildingDTO building1 = buildingService.createUpdateBuilding(requestDTO);
		BuildingJPA buildingJPA = buildingRepository.findById(building1.getId()).orElse(null);
		BuildingDTO building2 = null;
		if (buildingJPA != null)
			building2 = buildingJPA.createDTO();
		assertThat(building1).usingRecursiveComparison().isEqualTo(building2);
		assertEquals(requestDTO.getSize(), building1.getSize());
	}

	@Test
	public void updateBuilding_test() {
		BuildingDTO requestDTO = BuildingUtils.createBuildingDTO();
		BuildingDTO building1 = buildingService.createUpdateBuilding(requestDTO);
		requestDTO.setType("INDUSTRIAL");
		requestDTO.setId(building1.getId());
		BuildingDTO building2 = buildingService.createUpdateBuilding(requestDTO);
		assertEquals("INDUSTRIAL", building2.getType());
		assertEquals(building1.getId(), building2.getId());
	}

	@Test
	public void createAddress_test() {
		AddressDTO addressDTO = AddressUtils.createAddressDTO();
		AddressJPA addressJPA = ReflectionTestUtils.invokeMethod(buildingService, "createAddress", addressDTO);
		assertEquals(addressDTO.getCity(), addressJPA.getCity());
	}

	@Test
	public void createUpdateBuilding_ThrowsNotFoundException_test() {
		BuildingDTO requestDTO = BuildingUtils.createBuildingDTO();
		requestDTO.setId(9090909090L);
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			buildingService.createUpdateBuilding(requestDTO);
		});
		assertEquals("Building with id: " + 9090909090L + " not found.", notFoundException.getMessage());
	}
	
	@Test
	public void createUpdateBuilding_ThrowsNotFoundException_InvalidOwnerId_test() {
		BuildingJPA buildingJPA = BuildingUtils.createBuilding(999L, new OwnerJPA());
		Long id = buildingRepository.save(buildingJPA).getId();
		BuildingDTO requestDTO = BuildingUtils.createBuildingDTO();
		requestDTO.setOwnerId(9090909090L);
		requestDTO.setId(id);
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			buildingService.createUpdateBuilding(requestDTO);
		});
		assertEquals("Owner with id: " + 9090909090L + " not found.", notFoundException.getMessage());
	}

	@Test
	public void createUpdateBuilding_ThrowsUnprocessableEntityException_test() {
		assertThrows(UnprocessableEntityException.class, () -> {
			buildingService.createUpdateBuilding(null);
		});
	}

	@Test
	public void getAllBuildings_test() {
		BuildingDTO building = buildingRepository.save(BuildingUtils.createBuilding(9999L, new OwnerJPA())).createDTO();
		List<BuildingDTO> buildings = buildingService.getAllBuildings();
		assertThat(building).usingRecursiveComparison().isEqualTo(buildings.get(buildings.size() - 1));
	}

	@Test
	public void getBuildingsByOwnerId_test() {
		OwnerJPA ownerJPA = ownerRepository.save(OwnerUtils.createOwner());
		List<BuildingDTO> buildingList1 = buildingService.getBuildingsByOwnerId(ownerJPA.getId());
		List<BuildingDTO> buildingList2 = ownerJPA.getBuildings().stream().map(BuildingJPA::createDTO)
				.collect(Collectors.toList());
		assertThat(buildingList1).usingRecursiveComparison().isEqualTo(buildingList2);
	}

}
