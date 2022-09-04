package rerfb.enums;

import lombok.Getter;

@Getter
public enum PropertyType {

	APARTMENT(1.1),
	HOUSE(1.2),
	INDUSTRIAL(1.3),
	RESIDENTIAL(1.4),
	INSTITUTIONAL(1.5),
	BUSINESS(1.6),
	STORAGE(1.7),
	EDUCATIONAL(1.8);

	private final double taxRate;

	private PropertyType(double taxRate) {
		this.taxRate = taxRate;
	}
}
