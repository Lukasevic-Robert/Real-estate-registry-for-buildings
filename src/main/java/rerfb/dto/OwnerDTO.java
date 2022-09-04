package rerfb.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<BuildingDTO> buildings;

}
