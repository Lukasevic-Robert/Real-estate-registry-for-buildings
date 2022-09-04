package rerfb.testUtils;

import rerfb.dto.OwnerDTO;
import rerfb.model.OwnerJPA;

public class OwnerUtils {
	
	public static OwnerJPA createOwner() {
		OwnerJPA ownerJPA = new OwnerJPA();
		ownerJPA.setId(999L);
		ownerJPA.setEmail("johndoe@mail.com");
		ownerJPA.setFirstName("John");
		ownerJPA.setLastName("Doe");
		ownerJPA.setBuildings(BuildingUtils.createBuildingList(3, ownerJPA));
		return ownerJPA;
	}
	
	public static OwnerDTO createOwnerDTO() {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setFirstName("Bob");
		ownerDTO.setLastName("Marley");
		ownerDTO.setEmail("bob.marley@mail.com");
		return ownerDTO;
	}
}
