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

import rerfb.model.OwnerJPA;
import rerfb.repository.BuildingRepository;
import rerfb.testUtils.BuildingUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BuildingControllerTest {
	
	private final String URI = "/api/building";
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	BuildingRepository buildingRepository;
	
	@Test
	public void getBuildingById_test() throws Exception {
		
		Long id = buildingRepository.save(BuildingUtils.createBuilding(999L, new OwnerJPA())).getId();
		
		mockMvc
				.perform(get(URI + "/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size", is(999.0)));
	}
	
	@Test
	public void getBuildingList_test() throws Exception {
		
		buildingRepository.save(BuildingUtils.createBuilding(999L, new OwnerJPA()));
		
		mockMvc
				.perform(get(URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[-1]size", is(999.0)));
	}
	
	@Test
	public void createUpdateBuilding_test() throws Exception {
		String json = "{\"marketValue\": 99999,\"size\": 888,\"type\": \"HOUSE\"}";
		
		mockMvc
				.perform(post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size").value(888.0));

	}
	
	@Test
	public void deleteBuildingById_test() throws Exception {
		
		Long id = buildingRepository.save(BuildingUtils.createBuilding(999L, new OwnerJPA())).getId();
		
		mockMvc
				.perform(delete(URI + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	

}
