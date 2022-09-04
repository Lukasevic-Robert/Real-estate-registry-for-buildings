package rerfb.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.dto.OwnerDTO;
import rerfb.exceptions.NotFoundException;
import rerfb.exceptions.UnprocessableEntityException;
import rerfb.model.OwnerJPA;
import rerfb.repository.OwnerRepository;
import rerfb.testUtils.OwnerUtils;

@SpringBootTest
public class OwnerServiceImplTest {

	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	OwnerService ownerService;

	@Test
	public void getOwnerById_test() {
		OwnerDTO owner1 = ownerRepository.save(OwnerUtils.createOwner()).createDTO();
		OwnerDTO owner2 = ownerService.getOwnerById(owner1.getId());
		assertThat(owner1).usingRecursiveComparison().isEqualTo(owner2);
	}

	@Test
	public void getOwnerById_ThrowsNotFoundException_test() {
		assertThrows(NotFoundException.class, () -> {
			ownerService.getOwnerById(9090909090L);
		});
	}

	@Test
	public void deleteOwnerById_test() {
		Long id = ownerRepository.save(OwnerUtils.createOwner()).getId();
		ownerService.deleteById(id);
		assertNull(ownerRepository.findById(id).orElse(null));
	}

	@Test
	public void deleteOwnerById_ThrowsNotFoundException_test() {
		assertThrows(NotFoundException.class, () -> {
			ownerService.deleteById(9090909090L);
		});
	}

	@Test
	public void createOwner_test() {
		OwnerDTO requestDTO = OwnerUtils.createOwnerDTO();
		OwnerDTO owner1 = ownerService.createUpdateOwner(requestDTO);
		OwnerJPA ownerJPA = ownerRepository.findById(owner1.getId()).orElse(null);
		OwnerDTO owner2 = null;
		if (ownerJPA != null)
			owner2 = ownerJPA.createDTO();
		assertThat(owner1).usingRecursiveComparison().isEqualTo(owner2);
		assertEquals(requestDTO.getEmail(), owner1.getEmail());
	}

	@Test
	public void updateOwner_test() {
		OwnerDTO requestDTO = OwnerUtils.createOwnerDTO();
		OwnerDTO owner1 = ownerService.createUpdateOwner(requestDTO);
		requestDTO.setFirstName("Lambada");
		requestDTO.setId(owner1.getId());
		OwnerDTO owner2 = ownerService.createUpdateOwner(requestDTO);
		assertEquals("Lambada", owner2.getFirstName());
		assertEquals(owner1.getId(), owner2.getId());
	}

	@Test
	public void createUpdateOwner_ThrowsNotFoundException_test() {
		OwnerDTO requestDTO = OwnerUtils.createOwnerDTO();
		requestDTO.setId(9090909090L);
		assertThrows(NotFoundException.class, () -> {
			ownerService.createUpdateOwner(requestDTO);
		});
	}

	@Test
	public void createUpdateOwner_ThrowsUnprocessableEntityException_test() {
		assertThrows(UnprocessableEntityException.class, () -> {
			ownerService.createUpdateOwner(null);
		});
	}

	@Test
	public void getAllOwners_test() {
		OwnerDTO owner = ownerRepository.save(OwnerUtils.createOwner()).createDTO();
		List<OwnerDTO> owners = ownerService.getAllOwners();
		assertThat(owner).usingRecursiveComparison().isEqualTo(owners.get(owners.size() - 1));
	}
}
