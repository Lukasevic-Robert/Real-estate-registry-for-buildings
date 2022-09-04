package rerfb.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rerfb.dto.AddressDTO;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "addresses")
public class AddressJPA extends BaseEntity {

	private String city;

	private String street;

	private String number;

	public AddressDTO createDTO() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(getId());
		addressDTO.setCity(city);
		addressDTO.setStreet(street);
		addressDTO.setNumber(number);
		return addressDTO;
	}

	public AddressJPA doUpdate(AddressDTO addressDTO) {
		setCity(addressDTO.getCity());
		setStreet(addressDTO.getStreet());
		setNumber(addressDTO.getNumber());
		return this;
	}
}
