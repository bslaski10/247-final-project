package model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
	private UUID id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;

	public User(String userName, String password, String firstName, String lastName) {
		this.id = UUID.randomUUID();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(UUID id, String userName, String password, String firstName, String lastName) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
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
	
}
