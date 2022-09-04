package rerfb.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.dto.AddressDTO;
import rerfb.exceptions.NotFoundException;
import rerfb.repository.AddressRepository;
import rerfb.testUtils.AddressUtils;

@SpringBootTest
public class AddressServiceImplTest {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AddressService addressService;

	@Test
	public void getAddressById_test() {
		AddressDTO address1 = addressRepository.save(AddressUtils.createAddress()).createDTO();
		AddressDTO address2 = addressService.getAddressById(address1.getId());
		assertThat(address1).usingRecursiveComparison().isEqualTo(address2);
	}

	@Test
	public void getAddressById_ThrowsNotFoundException_test() {
		assertThrows(NotFoundException.class, () -> {
			addressService.getAddressById(9090909090L);
		});
	}

	@Test
	public void getAllAddresses_test() {
		AddressDTO address = addressRepository.save(AddressUtils.createAddress()).createDTO();
		List<AddressDTO> addresses = addressService.getAllAddresses();
		assertThat(address).usingRecursiveComparison().isEqualTo(addresses.get(addresses.size() - 1));
	}
}
