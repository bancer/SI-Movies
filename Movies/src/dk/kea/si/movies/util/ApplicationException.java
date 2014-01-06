package dk.kea.si.movies.util;


public class ApplicationException extends RuntimeException {
	
	public static final String AUTH_EXCEPTION = "Only authenticated users are allowed to access this page.";

//	public ApplicationException() {
//		// TODO Auto-generated constructor stub
//	}
//
	public ApplicationException(String message) {
		super(message);
		printStackTrace();
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		printStackTrace();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		printStackTrace();
	}

}
