package rerfb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDTO {

	private Long id;
	private AddressDTO address;
	private Long ownerId;
	private double size;
	private double marketValue;
	private String type;
}
