package dk.kea.si.movies.helpers;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.util.AppUtils;

public class UserHelper extends AHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3965830450137570307L;
	
	private User user;
	
	public UserHelper() {
		this(new User());
	}

	public UserHelper(User user) {
		this.user = user;
	}

	public void setUsername(String userName) {
		try {
			user.setUserName(userName);
		} catch (IllegalArgumentException e) {
			getErrors().put("username", e.getMessage());
		}
	}
	
	public String getUsername() {
		if(user.getUserName() != null) {
			return escapeXml(user.getUserName());
		} else {
			return ""; 
		}
	}

	public void setPassword(String password) {
		try {
			user.setPassword(password);
		} catch (IllegalArgumentException e) {
			getErrors().put("password", e.getMessage());
		}
	}
	
	public String getPassword() {
		if(user.getPassword() != null) {
			return escapeXml(user.getPassword());
		} else {
			return ""; 
		}
	}
	
	public void setEmail(String email) {
		try {
			user.setEmail(email);
		} catch (IllegalArgumentException e) {
			getErrors().put("email", e.getMessage());
		}
	}
	
	public String getEmail() {
		if(user.getEmail() != null) {
			return escapeXml(user.getEmail());
		} else {
			return ""; 
		}
	}
	
	public void comparePassword(String pass) {
		if(!user.getPassword().equals(AppUtils.sha256(pass))) {
			getErrors().put("repeatpassword", "Passwords do not match.");
		}
	}
}
