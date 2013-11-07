package dk.kea.si.movies.domain;

import java.util.ArrayList;

public class User extends DomainObject {

	private String username;

	private String password;

	private String email;

	private String phone;

	private String address;

	private String firstName;

	private String lastName;

	private ArrayList<OpenID> openIds;

	public User() {

		openIds = new ArrayList<OpenID>(6);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		if (username != null && username.length() < 1) {
			throw new IllegalArgumentException("Username must not be empty.");
		}
		if (username != null && username.length() > 50) {
			throw new IllegalArgumentException(
					"Username must not be longer than 12 characters.");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		if (password != null && password.length() < 1) {
			throw new IllegalArgumentException("PW must not be empty.");
		}
		if (password != null && password.length() > 255) {
			throw new IllegalArgumentException(
					"PW must not be longer than 255 characters.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		if (email != null && email.length() > 50) {
			throw new IllegalArgumentException(
					"Email must not be longer than 50 characters.");
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		if (phone != null && phone.length() > 12) {
			throw new IllegalArgumentException(
					"Phone must not be longer than 12 characters.");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		if (address != null && address.length() < 1) {
			throw new IllegalArgumentException("Address must not be empty.");
		}
		if (address != null && address.length() > 250) {
			throw new IllegalArgumentException(
					"Address must not be longer than 250 characters.");
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		if (firstName != null && firstName.length() < 1) {
			throw new IllegalArgumentException("Firstname must not be empty.");
		}
		if (firstName != null && firstName.length() > 150) {
			throw new IllegalArgumentException(
					"Firstname must not be longer than 150 characters.");
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		if (lastName != null && lastName.length() < 1) {
			throw new IllegalArgumentException("Lastname must not be empty.");
		}
		if (lastName != null && lastName.length() > 150) {
			throw new IllegalArgumentException(
					"Lastname must not be longer than 150 characters.");
		}
	}

	@Override
	public String toString() {
		String result = "[" + address + ", " + email + ", " + firstName + ", "
				+ lastName + ", " + password + ", "
				+ phone + ", " + username + ", [";
		for (int i = 0; i < openIds.size(); i++) {
			result += openIds.get(i).toString();
		}
		result += "] ]";
		return result;
	}

	public ArrayList<OpenID> getOpenIds() {
		return openIds;
	}

	public void setOpenIds(ArrayList<OpenID> identifiers) {
		this.openIds = identifiers;
	}

	public void addOpenId(OpenID openId) {
		openIds.add(openId);
	}

	public String getIdentifier(int index) {
		if (openIds.size() > index) {
			return openIds.get(index).getIdentifier();
		} else {
			return null;
		}
	}
}
