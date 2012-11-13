package com.reaper.server;

public class UserData {
	private final String user;
	private String passwordHash;
	private String mail;
	private boolean isLoggedOn;
	
	public UserData(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	// Shouldn't be used unless we change hash salt
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setLoggedOn(boolean isLoggedOn) {
		this.isLoggedOn = isLoggedOn;
	}
	
	public boolean isLoggedOn() {
		return isLoggedOn;
	}
}
