package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.reaper.shared.Topic;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void login(String input, String password, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void register(String username, String password, String passwordVerify,
			String mail, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getTopics(String username, String passwordHash, String tags,
			AsyncCallback<ArrayList<Topic>> callback);

	void createTopic(String username, String passwordHash, String title,
			String firstPost, int tag, AsyncCallback<Void> callback);
}
