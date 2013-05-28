package dk.kea.si.movies.util;


public class ApplicationException extends RuntimeException {

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
