package model;

import java.util.ArrayList;

public class Users {
	private static Users users;
	private ArrayList<User> userList;
	
	private Users() {
		userList = Database.getUsers();
	}
	
	public static Users getInstance() {
		if(users == null) {
			users = new Users();
		}
		
		return users;
	}

	public boolean haveUser(String userName, String password) {
		for(User user : userList) {
			if(user.getUserName().equals(userName)) {
				return true;
			}
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
	public User getUser(String userName, String password) {
		for(User user : userList) {
			if(user.getUserName().equals(userName)) {
				return user;
			}
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		
		return null;
	}

	
	
	public ArrayList<User> getUsers() {
		return userList;
	}
	
	public boolean addUser(String userName, String password, String firstName, String lastName) {
		if(haveUser(userName, password))return false;
		
		userList.add(new User(userName, password, firstName, lastName));
		return true;

	}
	
	public void saveUsers() {
		Database.saveUsers();
	}
}
