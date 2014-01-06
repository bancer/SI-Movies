package dk.kea.si.movies.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends DomainObject {

	private static final int MAX_OPEN_IDS = 6;

	private String displayName;

	private String password;

	private String email;

	private String phone;

	private String address;

	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String salt;

	private ArrayList<OpenID> openIds;
	
	private Timestamp blockedUntil;

	public User() {

		openIds = new ArrayList<OpenID>(MAX_OPEN_IDS);
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String username) {
		this.displayName = username;
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

	public void setPassword(String pass) {
		this.password = pass;		
		if (pass != null && pass.length() < 1) {
			throw new IllegalArgumentException("Password must not be empty.");
		}
		if (pass != null && pass.length() < 8) {
			throw new IllegalArgumentException(
					"Password must not be shorter than 8 characters.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		if (email != null) {
			if (email.length() < 1) {
				throw new IllegalArgumentException("Email must not be empty.");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
				Matcher m = p.matcher(email);
				if(!m.matches()) {
					throw new IllegalArgumentException("Invalid email address.");
				}
			}
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
		return String
				.format("User [displayName=%s, password=%s, email=%s, phone=%s, address=%s, firstName=%s, lastName=%s, userName=%s, salt=%s, openIds=%s, blockedUntil=%s, getId()=%s]",
						displayName, password, email, phone, address,
						firstName, lastName, userName, salt, openIds,
						blockedUntil, getId());
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		if (userName != null && userName.length() < 1) {
			throw new IllegalArgumentException("Username must not be empty.");
		}
		if (userName != null && userName.length() > 20) {
			throw new IllegalArgumentException(
					"Username must not be longer than 20 characters.");
		}
		if (userName != null && userName.length() < 4) {
			throw new IllegalArgumentException(
					"Username must not be shorter than 4 characters.");
		}
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Timestamp getBlockedUntil() {
		return blockedUntil;
	}

	public void setBlockedUntil(Timestamp blockedUntil) {
		this.blockedUntil = blockedUntil;
	}
}
