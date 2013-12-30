package dk.kea.si.movies.helpers;

import java.util.ArrayList;
import java.util.List;

import dk.kea.si.movies.domain.User;
import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.RepeatCharacterRegexRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.WhitespaceRule;

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
			return sanitize(user.getUserName());
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
			return sanitize(user.getPassword());
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
			return sanitize(user.getEmail());
		} else {
			return ""; 
		}
	}
	
	public void comparePassword(String pass) {
		if(!user.getPassword().equals(pass)) {
			getErrors().put("repeatpassword", "Passwords do not match.");
		}
	}
	
	public void validatePassword(String pass) {
		// don't allow whitespace
		WhitespaceRule whitespaceRule = new WhitespaceRule();

		// control allowed characters
		CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
		// require at least 1 digit in passwords
		charRule.getRules().add(new DigitCharacterRule(1));
		// require at least 1 non-alphanumeric char
		charRule.getRules().add(new NonAlphanumericCharacterRule(1));
		// require at least 1 upper case char
		charRule.getRules().add(new UppercaseCharacterRule(1));
		// require at least 1 lower case char
		charRule.getRules().add(new LowercaseCharacterRule(1));
		// require at least 3 of the previous rules be met
		charRule.setNumberOfCharacteristics(3);

		// don't allow alphabetical sequences
		AlphabeticalSequenceRule alphaSeqRule = new AlphabeticalSequenceRule();

		// don't allow qwerty sequences
		QwertySequenceRule qwertySeqRule = new QwertySequenceRule();

		// don't allow 4 repeat characters
		RepeatCharacterRegexRule repeatRule = new RepeatCharacterRegexRule(4);

		// group all rules together in a List
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(whitespaceRule);
		ruleList.add(charRule);
		ruleList.add(alphaSeqRule);
		ruleList.add(qwertySeqRule);
		ruleList.add(repeatRule);

		PasswordValidator validator = new PasswordValidator(ruleList);
		PasswordData passwordData = new PasswordData(new Password(pass));

		RuleResult result = validator.validate(passwordData);
		if (!result.isValid()) {
			String message = "";
			for (String msg : validator.getMessages(result)) {
				message += (msg + "\n");
			}
			getErrors().put("password", message);
		}

	}

	public User getUser() {
		return this.user;
	}
}
