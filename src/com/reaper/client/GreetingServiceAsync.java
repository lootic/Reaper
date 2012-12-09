package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.reaper.shared.Bet;
import com.reaper.shared.Tag;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void login(String input, String password, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void register(String username, String password, String passwordVerify,
			String mail, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getBets(ArrayList<Tag> tags,
			AsyncCallback<ArrayList<Bet>> asyncCallback);

	void logout(AsyncCallback<Void> callback);
}
