package rerfb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.dto.AddressDTO;
import rerfb.testUtils.AddressUtils;

@SpringBootTest
public class AddressJPATest {

	@Test
	public void doUpdate_test() {
		AddressJPA addressJPA = new AddressJPA();
		AddressDTO addressDTO = AddressUtils.createAddressDTO();
		addressJPA.doUpdate(AddressUtils.createAddressDTO());
		assertEquals(addressJPA.getStreet(), addressDTO.getStreet());
	}
}
