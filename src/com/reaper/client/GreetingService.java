package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.reaper.shared.Topic;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String login(String username, String password)
			throws IllegalArgumentException;

	String register(String username, String password, String passwordVerify,
			String mail) throws IllegalArgumentException;

	ArrayList<Topic> getTopics(String username, String passwordHash, String tags)
			throws IllegalArgumentException;

	void createTopic(String username, String passwordHash, String title,
			String firstPost, int tag);
}
