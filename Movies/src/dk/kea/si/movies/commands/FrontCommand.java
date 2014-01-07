package dk.kea.si.movies.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dk.kea.si.movies.components.Auth;
import dk.kea.si.movies.components.Auth.Action;
import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.domain.User.Role;
import dk.kea.si.movies.persistence.core.PersistenceFacade;
import dk.kea.si.movies.util.AppUtils;
import dk.kea.si.movies.util.ApplicationException;
import dk.kea.si.movies.util.Constants;


public abstract class FrontCommand {

	protected ServletContext context;
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;

	protected String rottenTomatoesApiKey;

	protected String googleApiKey;
	
	protected String openidApiKey;  
	
	public abstract void process() throws ServletException, IOException;
	
	public abstract void processPost() throws ServletException, IOException;
	
	public void init(ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		this.context = context;
		this.request = request;
		this.response = response;
		this.rottenTomatoesApiKey = request.getSession().getServletContext()
				.getInitParameter("rottentomatoes-api-key");
		this.googleApiKey = request.getSession().getServletContext()
				.getInitParameter("google-api-key");
		this.openidApiKey = request.getSession().getServletContext()
				.getInitParameter("openid-api-key");
	}
	
	protected void forward(String target) throws ServletException, IOException {
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
	
	protected PersistenceFacade getStorage() {
		return PersistenceFacade.getInstance();
	}

	protected void startSession(User user) {
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_USER_KEY, user);
		setCSRFProtectionKey(session);
	}

	private void setCSRFProtectionKey(HttpSession session) {
		//if(session.getAttribute(Constants.SESSION_CSRF_KEY) == null) {
			String csrfKey = AppUtils.generateSalt();
			session.setAttribute(Constants.SESSION_CSRF_KEY, csrfKey);
		//}
	}
	
	protected void setCSRFProtectionKey() {
		setCSRFProtectionKey(request.getSession());
	}

	protected void endSession() {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	protected boolean hasValidCSRFToken() {
		String submittedCSRFKey = request.getParameter(Constants.SESSION_CSRF_KEY);
		HttpSession session = request.getSession();
		//String CSRFKey = AppUtils.sha256(session.getId());
		String CSRFKey = (String) session.getAttribute(Constants.SESSION_CSRF_KEY);
		//System.out.println(submittedCSRFKey);
		//System.out.println(CSRFKey);
//		if(submittedCSRFKey == null) {
//			//response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "CSRF attempt has been detected. Cannot process the form.");
//			throw new IllegalStateException("CSRF attempt has been detected. Cannot process the form.");
//			//TODO: log CSRF attempt
//			//return false;
//		} else if(!CSRFKey.equals(submittedCSRFKey)) {
//			//response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Your session has probably been expired. Cannot process the form. Refresh the previous page.");
//			throw new IllegalStateException("Your session has probably been expired. Cannot process the form. Refresh the previous page.");
//			
//			//return false;
//		}
		if(submittedCSRFKey != null && CSRFKey.equals(submittedCSRFKey)) {
			return true;
		} else {
			String msg = "Your session has probably been expired. Cannot process the form. Refresh the page.";
			request.setAttribute(Constants.ERROR_MESSAGE_KEY, msg);
			//TODO: log possible CSRF attempt
			return false;
		}
	}
	
	protected boolean isAuthenticatedUser() {
		return request.getSession().getAttribute(Constants.SESSION_USER_KEY) != null;
	}
	
	protected void rejectUnauthenticatedUser() {
		if(!isAuthenticatedUser()) {
			throw new ApplicationException(ApplicationException.AUTH_EXCEPTION);
		}
	}
	

	
	protected boolean isAuthorized(Action action) {
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);	
		return Auth.isAuthorized(action, user.getRole());
	}
}
