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

import rerfb.repository.AddressRepository;
import rerfb.testUtils.AddressUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {
	
	private final String URI = "/api/address";

	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AddressRepository addressRepository;
	
	@Test
	public void getAddressById_test() throws Exception {
		Long id = addressRepository.save(AddressUtils.createAddress()).getId();
		
		mockMvc
				.perform(get(URI + "/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.number", is("555A")));

	}
	
	@Test
	public void getAddressList_test() throws Exception {
		addressRepository.save(AddressUtils.createAddress());
		
		mockMvc
				.perform(get(URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[-1]number", is("555A")));

	}
}
