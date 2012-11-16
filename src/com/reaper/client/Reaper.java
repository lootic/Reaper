package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.reaper.shared.Bet;

/**
 * Mediator class
 * 
 * @author lootic
 * 
 */
public class Reaper implements EntryPoint {

	private final DockPanel panel = new DockPanel();
	private final FlowPanel betsPanel = new FlowPanel();
	private final FlowPanel headerPanel = new FlowPanel();
	private final Button loginButton = new Button("Login");
	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	private final LoginWidget login = new LoginWidget();

	public void onModuleLoad() {
		//create
		ScrollPanel scrollPanel = new ScrollPanel();
		
		//customize
		RootPanel.get().setStyleName("root");
		
		//connect
		RootPanel.get().add(panel);
		scrollPanel.add(betsPanel);
		login.getSendButton().addClickHandler(new SendButtonHandler(this));
		panel.add(scrollPanel, DockPanel.CENTER);
		panel.add(headerPanel, DockPanel.NORTH);
		headerPanel.add(new Label("REAPER - betting iz seriouz buzinezz"));
		
		//init
		getBets();
	}

	private void getBets() {
		service.getBets(null, new AsyncCallback<ArrayList<Bet>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(ArrayList<Bet> bets) {
				for (Bet bet : bets) {
					betsPanel.add(new BetWidget(bet));
				}
			}
		});
	}

	public void register() {
		service.register(login.getUser(), login.getPassword(), login.getPasswordVerify(),
				login.getPasswordVerify(), new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						login.setErrorFeedbackmessage(caught.getMessage());
					}

					public void onSuccess(String result) {
						login.setErrorFeedbackmessage(result);
					}
				});

	}

	public void login() {
		service.login(login.getUser(), login.getPassword(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						login.setErrorFeedbackmessage(caught.getMessage());
					}

					public void onSuccess(String result) {
						getBets();
					}
				});
	}
	
	public void send() {
		if (login.isRegisterToggled()) {
			register();
		} else {
			login();
		}
	}
}