package rerfb.testUtils;

import rerfb.dto.AddressDTO;
import rerfb.model.AddressJPA;

public class AddressUtils {

	public static AddressJPA createAddress() {
		AddressJPA addressJPA = new AddressJPA();
		addressJPA.setId(999L);
		addressJPA.setCity("Helsinki");
		addressJPA.setStreet("Van Helsinki");
		addressJPA.setNumber("555A");
		return addressJPA;
	}

	public static AddressDTO createAddressDTO() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Xonio");
		addressDTO.setStreet("Ibababaubaba u.");
		addressDTO.setNumber("25");
		return addressDTO;
	}
}
