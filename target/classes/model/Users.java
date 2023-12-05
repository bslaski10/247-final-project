import java.util.ArrayList;

public class Users {
	private static Users instance;
	private ArrayList<User> userList;
	
	private Users() {
		userList = DataLoader.getUsers();
	}
	
	public static Users getInstance() {
		if(instance == null) {
			instance = new Users();
		}
		
		return instance;
	}

	public boolean haveUser(String userName) {
		for(User user : userList) {
			if(user.getUsername().equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	public User getUser(String userName) {
		for(User user : userList) {
			if(user.getUsername().equals(userName)) {
				return user;
			}
		}
		
		return null;
	}
	
	public ArrayList<User> getUsers() {
		return userList;
	}
	
	public boolean addUser(String username, String password, String firstName, String lastName) {
		if(haveUser(username))return false;
		
		userList.add(new User(username, password, firstName, lastName));
		return true;
	}

	public User checkUser(String username, String password) {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                if (password.equals(user.getPassword())) {
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
	
	public void saveUsers() {
		DataWriter.saveUsers();
	}
}