package swag.model;

@javax.persistence.Embeddable
public class Address {

	private String state;

	private String city;

	private String postalCode;

	private String street;

	public Address() {
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
