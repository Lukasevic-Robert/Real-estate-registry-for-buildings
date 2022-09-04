package rerfb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.dto.OwnerDTO;
import rerfb.testUtils.OwnerUtils;

@SpringBootTest
public class OwnerJPATest {

	@Test
	public void doUpdate_test() {
		OwnerJPA ownerJPA = new OwnerJPA();
		OwnerDTO ownerDTO = OwnerUtils.createOwnerDTO();
		ownerJPA.doUpdate(ownerDTO);
		assertEquals(ownerDTO.getEmail(), ownerJPA.getEmail());
	}

	@Test
	public void createDTO_test() {
		OwnerJPA ownerJPA = OwnerUtils.createOwner();
		OwnerDTO ownerDTO = ownerJPA.createDTO();
		assertEquals(ownerJPA.getEmail(), ownerDTO.getEmail());
	}
}
