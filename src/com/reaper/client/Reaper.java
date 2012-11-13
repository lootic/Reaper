package com.reaper.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.reaper.shared.Topic;

public class Reaper implements EntryPoint {

	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	private final Button createTopicButton = new Button("Create new topic");
	private final Login login = new Login();

	private String passwordHash;

	public void onModuleLoad() {
		
		login.setRegisterMode(false);

		RootPanel.get().setStyleName("root");
		RootPanel.get("login").add(login);
		RootPanel.get("login").setStyleName("login");
		class CreateTopicButtonHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				service.createTopic(login.getUser(), passwordHash, "lol",
						"din Mamma är bög", 1, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Void result) {
								loadLogin();
							}
						});
			}
		}

		class SendButtonHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			private void sendNameToServer() {
				// First, we validate the input.
				String textToServer = login.getUser();
				String passwordText = login.getPassword();
				String passwordVerifyText = login.getPasswordVerify();
				String mailText = login.getMail();

				// Then, we send the input to the server.
				if (login.isRegisterToggled()) {
					service.register(textToServer, passwordText,
							passwordVerifyText, mailText,
							new AsyncCallback<String>() {
								public void onFailure(Throwable caught) {
									login.setErrorFeedbackmessage(caught
											.getMessage());
								}

								public void onSuccess(String result) {
									login.setErrorFeedbackmessage(result);
								}
							});
				} else {
					service.login(textToServer, passwordText,
							new AsyncCallback<String>() {
								public void onFailure(Throwable caught) {
									login.setErrorFeedbackmessage(caught
											.getMessage());
								}

								public void onSuccess(String result) {
									passwordHash = result;
									loadLogin();
								}
							});
				}

			}
		}

		// Add a handler to send the name to the server
		SendButtonHandler sendHandler = new SendButtonHandler();
		login.getSendButton().addClickHandler(sendHandler);
		createTopicButton.addClickHandler(new CreateTopicButtonHandler());
	}

	private void loadLogin() {
		Label hello = new Label("hello");
		RootPanel.get().clear();
		RootPanel.get().add(hello);
		RootPanel.get().add(createTopicButton);
		service.getTopics(login.getUser(), passwordHash, null,
				new AsyncCallback<ArrayList<Topic>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(ArrayList<Topic> topics) {
						for (Topic topic : topics) {
							RootPanel.get().add(new Post(topic.getTitle()));
						}
					}
				});
	}
}
