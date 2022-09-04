package rerfb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import rerfb.repository.OwnerRepository;
import rerfb.testUtils.OwnerUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

	private final String URI = "/api/owner";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	OwnerRepository ownerRepository;

	@Test
	public void getOwnerById_test() throws Exception {
		Long id = ownerRepository.save(OwnerUtils.createOwner()).getId();
		
		mockMvc
				.perform(get(URI + "/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName", is("John")));

	}
	
	@Test
	public void getOwnerList_test() throws Exception {
		ownerRepository.save(OwnerUtils.createOwner());
		
		mockMvc
				.perform(get(URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[-1]firstName", is("John")));

	}
	
	@Test
	public void createUpdateOwner_test() throws Exception {
		String json = "{\"email\": \"ponas.bynas@mail.com\",\"firstName\": \"Ponas\",\"lastName\": \"Bynas\"}";
		
		mockMvc
				.perform(post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Ponas"));

	}
	
	@Test
	public void deleteOwnerById_test() throws Exception {
		Long id = ownerRepository.save(OwnerUtils.createOwner()).getId();
		
		mockMvc
				.perform(delete(URI + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}
