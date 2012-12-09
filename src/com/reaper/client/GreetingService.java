package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.reaper.shared.Bet;
import com.reaper.shared.Tag;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String login(String username, String password)
			throws IllegalArgumentException;
	
	void logout();

	String register(String username, String password, String passwordVerify,
			String mail) throws IllegalArgumentException;

	ArrayList<Bet> getBets(ArrayList<Tag> tags)
			throws IllegalArgumentException;

}
