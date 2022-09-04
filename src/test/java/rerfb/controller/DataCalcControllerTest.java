package rerfb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import rerfb.model.OwnerJPA;
import rerfb.repository.OwnerRepository;
import rerfb.testUtils.OwnerUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataCalcControllerTest {

	private final String URI = "/api/calc";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	OwnerRepository ownerRepository;

	@Test
	public void getYearlyRealEstateTax_test() throws Exception {

		OwnerJPA ownerJPA = ownerRepository.save(OwnerUtils.createOwner());
		Long id = ownerJPA.getId();
		double taxes = ownerJPA.getBuildings().stream()
				.mapToDouble(b -> b.getMarketValue() * b.getType().getTaxRate() / 100).sum();

		mockMvc
				.perform(get(URI + "/tax/yearly-real-estate/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.['taxes for owner with id: " + id + "']", is(taxes)));
	}

}
