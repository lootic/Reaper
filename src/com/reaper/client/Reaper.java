package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
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
	private final Button loginButton = new Button("Login");
	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	private final LoginWidget login = new LoginWidget();
	private final Button popupLoginButton = new Button("Login");
	private final PopupPanel loginPanel = new PopupPanel();

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
					loginPanel.show();
				}
			}
		});

		loginPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				login.setRegisterMode(false);
				int left = (Window.getClientWidth() - offsetWidth);
				int top = 40;
				loginPanel.setPopupPosition(left, top);
			}
		});

		RootPanel.get("main").add(new EmbeddedVideoWidget(
						"http://www.twitch.tv/widgets/live_embed_player.swf?channel=siglemic"));
		RootPanel.get("login").add(popupLoginButton);
	}

	private void getBets() {
		service.getBets(null, new AsyncCallback<ArrayList<Bet>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(ArrayList<Bet> bets) {
				for (Bet bet : bets) {
					RootPanel.get("main").add(new BetWidget(bet));
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