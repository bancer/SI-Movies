package dk.kea.si.movies.commands;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dk.kea.si.movies.domain.OpenID;
import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.util.ApplicationException;


public class SignInCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processPost() throws ServletException, IOException {
		HttpURLConnection connection = sendAuthenticationRequest();
		String userData = getUserData(connection);
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(userData);
			String status = (String) obj.get("stat");
			if(status.equals("ok")) {
				User parsedUser = parseUserData(obj);

				HttpSession session = request.getSession(true);
				session.setAttribute("authenticated.user", parsedUser);	
			} else {
				throw new ApplicationException("Not authenticated!");
			}
		} catch (ParseException e) {
			throw new ApplicationException(e);
		}
		//redirect to home page
		response.sendRedirect(request.getContextPath());
		
	}
	
	private User parseUserData(JSONObject obj) {
		JSONObject profile = (JSONObject) obj.get("profile");
		OpenID openId = new OpenID();
		openId.setIdentifier((String) profile.get("identifier"));
		openId.setProvider((String) profile.get("providerName"));
		User user = new User();
		user.addOpenId(openId);
		user.setUsername((String) profile.get("displayName"));
		user.setEmail((String) profile.get("email"));
		user.setPhone((String) profile.get("phoneNumber"));
		user.setAddress((String) profile.get("address"));
		JSONObject name = (JSONObject) profile.get("name");
		user.setFirstName((String) name.get("givenName"));
		user.setLastName((String) name.get("familyName"));
		return user;
	}

	private String getUserData(HttpURLConnection connection) throws IOException {
		Scanner scanner = new Scanner(connection.getInputStream());
		scanner.useDelimiter("\\A");
		return scanner.next();
	}

	private HttpURLConnection sendAuthenticationRequest()
			throws MalformedURLException, UnsupportedEncodingException,
			IOException, ProtocolException {
		// The user's browser will POST a token to your "token_url" you
		// specified to have them
		// redirected to after the auth process:
		String token = request.getParameter("token");
		// Do a request to the Janrain API with the token we just received.
		// see http://developers.janrain.com/documentation/api/auth_info/
		// You may wish to make this HTTP request with e.g. Apache HttpClient
		// instead.
		URL url = new URL("https://rpxnow.com/api/v2/auth_info");
		String params = String.format("apiKey=%s&token=%s",
				URLEncoder.encode(openidApiKey, "UTF-8"),
				URLEncoder.encode(token, "UTF-8"));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.connect();
		OutputStreamWriter writer = new OutputStreamWriter(
				connection.getOutputStream(), "UTF-8");
		writer.write(params);
		writer.close();
		return connection;
	}


}
