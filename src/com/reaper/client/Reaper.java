package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.reaper.shared.Bet;

/**
 * Mediator class
 * 
 * @author lootic
 * 
 */
public class Reaper implements EntryPoint {
	private final Button loginButton = new Button("Login");
	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	private final LoginWidget login = new LoginWidget();
	private final Button popupLoginButton = new Button("Login");
	private final LoginPanel loginPanel = new LoginPanel();
	private final BetListWidget betList = new BetListWidget();

	public void onModuleLoad() {
		// customize
		RootPanel.get().setStyleName("root");
		loginPanel.setStyleName("loginPanel");

		// connect
		loginPanel.add(login);
		login.getSendButton().addClickHandler(new SendButtonHandler(this));

		popupLoginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (loginPanel.isShowing()) {
					loginPanel.hide();
				} else {
					loginPanel.resize();
					loginPanel.show();
				}
			}
		});

		RootPanel.get("main").add(betList);
		RootPanel.get("main").add(new Counter("amount"));
		RootPanel.get("login").add(popupLoginButton);
		RootPanel.get("main").add(new NumberTextfield("Test of numbers"));
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
					betList.add(bet);
				}
			}
		});
	}

	public void register() {
		service.register(login.getUser(), login.getPassword(),
				login.getPasswordVerify(), login.getPasswordVerify(),
				new AsyncCallback<String>() {
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
						loginPanel.setWidget(new AccountWidget());
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

	public void logout() {
		service.logout(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Void result) {
				loginPanel.setWidget(login);
			}
		});
	}
}