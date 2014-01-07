package dk.kea.si.movies.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.domain.User.Role;

public class Auth {

	public enum Action {
		COMMENT_EDIT, COMMENT_DELETE
	};

	/**
	 * Access list contains a map of actions and allowed roles who can perform
	 * those actions.
	 */
	private static final HashMap<Action, List<Role>> accessList;
	
	static {
		accessList = new HashMap<Action, List<Role>>();
		List<Role> adminAndEditor = Arrays.asList(new Role[] {
				User.Role.EDITOR, User.Role.ADMIN });
		accessList.put(Action.COMMENT_DELETE, adminAndEditor);
		accessList.put(Action.COMMENT_EDIT, adminAndEditor);
	}

	public static boolean isAuthorized(Action action, Role role) {
		if (accessList.containsKey(action)) {
			return accessList.get(action).contains(role);
		} else {
			return true;
		}
	}
}
