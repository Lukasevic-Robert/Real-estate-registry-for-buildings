package rerfb.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rerfb.dto.OwnerDTO;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "owners")
public class OwnerJPA extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BuildingJPA> buildings;

	public OwnerDTO createDTO() {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(getId());
		ownerDTO.setFirstName(firstName);
		ownerDTO.setLastName(lastName);
		ownerDTO.setEmail(email);
		if (buildings != null && !buildings.isEmpty())
			ownerDTO.setBuildings(buildings.stream().map(BuildingJPA::createDTO).collect(Collectors.toList()));
		return ownerDTO;
	}

	public OwnerJPA doUpdate(OwnerDTO owner) {
		setFirstName(owner.getFirstName());
		setLastName(owner.getLastName());
		setEmail(owner.getEmail());
		return this;
	}
}
