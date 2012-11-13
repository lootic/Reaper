package com.reaper.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.reaper.client.GreetingService;
import com.reaper.shared.Topic;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	public static final int MAIL_VERIFY_TIME_LIMIT = 48; // specified in hours
	HashMap<String, UserData> userDB = new HashMap<String, UserData>();
	HashSet<String> registeredMails = new HashSet<String>();
	private String currentSalt;
	
	public String login(String username, String password)
			throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!userDB.containsKey(username)) {
			throw new IllegalArgumentException("Invalid username.");
		}
		String hash = BCrypt.hashpw(password, getSalt());

		if (!userDB.get(username).getPasswordHash().equals(hash)) {
			throw new IllegalArgumentException("Wrong password!");
		}
		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		userDB.get(username).setLoggedOn(true);

		return hash;
	}

	@Override
	public String register(String username, String password,
			String passwordVerify, String mail) {
		// check if mail could possibly be a mail
	
		if (userDB.containsKey(username)) {
			throw new IllegalArgumentException(
					"Someone else already uses that username.");
		}
		if (!password.equals(passwordVerify)) {
			throw new IllegalArgumentException(
					"The two password fields are not equal.");
		}
		/*if(mail.matches(".*@.*\\..*")){
			throw new IllegalArgumentException(
					"Not a valid mail.");
		}*/
		if (registeredMails.contains(mail)) {
			throw new IllegalArgumentException(
					"Mail is already in use by an other account.");
		}
		UserData newUser = new UserData(username);
		newUser.setMail(mail);
		newUser.setPasswordHash(BCrypt.hashpw(password, getSalt()));
		userDB.put(username, newUser);
		registeredMails.add(mail);
		return null;
	}

	private String getSalt() {
		if (currentSalt == null) {
			currentSalt = BCrypt.gensalt();
		}
		return currentSalt;
	}

	@Override
	public ArrayList<Topic> getTopics(String username, String passwordHash, String tags)
			throws IllegalArgumentException {
		System.out.println("hello!");
		
		DatabaseManagement.initIfNotInitiated();
		
		ArrayList<Topic> topics = new ArrayList<Topic>();
		Topic topic = new Topic();
		topic.setTitle("This is some text proving that it comes from the server.");
		topics.add(topic);
		
		return topics;
	}

	/**
	 * Verifies a request for something from the server, should be done at the
	 * start of most server actions.
	 * @param username the users chosen username.
	 * @param passwordHash the hash of the password the user got.
	 * @return whether the user is logged on or not.
	 */
	private boolean isUserLoggedOn(String username, String passwordHash) {
		// TODO Auto-generated method stub
		UserData user = userDB.get(username);
		if (user == null) {
			return false;
		}
		if(user.isLoggedOn() && user.getPasswordHash().equals(passwordHash)){
			return true;
		}
		return false;
	}

	@Override
	public void createTopic(String username, String passwordHash, String title,
			String firstPost, int tag) {

		if(!isUserLoggedOn(username, passwordHash)) {
			throw new IllegalArgumentException("Not logged on.");
		}
	//	DatabaseManagement.createTopic(title, firstPost, username, tag);
	}
}
