package rerfb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rerfb.exceptions.NotFoundException;
import rerfb.model.OwnerJPA;
import rerfb.repository.OwnerRepository;
import rerfb.testUtils.OwnerUtils;

@SpringBootTest
public class DataCalcServiceImplTest {

	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	DataCalcService dataCalcService;

	@Test
	public void getYearlyRealEstateTax_test() {
		OwnerJPA ownerJPA = ownerRepository.save(OwnerUtils.createOwner());
		Long id = ownerJPA.getId();
		double taxes = ownerJPA.getBuildings().stream()
				.mapToDouble(b -> b.getMarketValue() * b.getType().getTaxRate() / 100).sum();
		double actualTaxes = dataCalcService.getYearlyRealEstateTax(id);
		assertEquals(taxes, actualTaxes);
	}

	@Test
	public void getYearlyRealEstateTax_ThrowsNotFoundException_test() {
		assertThrows(NotFoundException.class, () -> {
			dataCalcService.getYearlyRealEstateTax(9090909090L);
		});
	}

}
