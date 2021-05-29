package datamodel;

public class Customer {
	protected final String id;
	protected String firstName;
	protected String lastName;
	protected String contact;

	protected Customer(String id, String name, String contact){
		this.id = id;
		this.contact = contact;
		this.lastName = name;
		this.firstName = "";
	}
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getContact () {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
}
