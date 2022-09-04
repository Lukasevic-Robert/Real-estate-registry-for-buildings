package rerfb.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rerfb.dto.BuildingDTO;
import rerfb.enums.PropertyType;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "buildings")
public class BuildingJPA extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonBackReference
	private OwnerJPA owner;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private AddressJPA address;

	private double size;

	@Column(name = "market_value")
	private double marketValue;

	@Enumerated(EnumType.STRING)
	private PropertyType type;

	public BuildingDTO createDTO() {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setId(getId());
		if (owner != null)
			buildingDTO.setOwnerId(owner.getId());
		if (address != null)
			buildingDTO.setAddress(address.createDTO());
		buildingDTO.setSize(size);
		buildingDTO.setMarketValue(marketValue);
		buildingDTO.setType(type.name());
		return buildingDTO;
	}

	public BuildingJPA doUpdate(BuildingDTO building) {
		setSize(building.getSize());
		setMarketValue(building.getMarketValue());
		setType(PropertyType.valueOf(building.getType()));
		return this;

	}
}
