package com.reaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Login extends FlexTable {
	private final Textfield nameField = new Textfield("username");
	private final Textfield mail = new Textfield("email");
	private final TextBox password = new Textfield("password");
	private final TextBox passwordVerify = new Textfield("verify password");
	final Button registerToggleButton = new Button(
			"I ain't got no motherfucking account!");
	
	private final Button sendButton = new Button();
	private final Label callbackValue = new Label();
	private boolean isRegisterToggled = false;

	Login() {
		sendButton.addStyleName("sendButton");
		this.setWidget(0, 0, nameField);
		this.setWidget(1, 0, password);
		this.setWidget(2, 0, passwordVerify);
		this.setWidget(3, 0, mail);
		this.setWidget(4, 0, registerToggleButton);
		this.setWidget(4, 1, sendButton);
		this.setWidget(5, 0, callbackValue);

		// Add a handler to send the name to the server
		RegisterButtonHandler registerHandler = new RegisterButtonHandler();
		registerToggleButton.addClickHandler(registerHandler);
		this.setVisible(true);
	}

	private class RegisterButtonHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (isRegisterToggled) {
				isRegisterToggled = false;
				mail.setVisible(false);
				passwordVerify.setVisible(false);
				registerToggleButton
						.setText("I ain't got no motherfucking account!");
				sendButton.setText("Login");
			} else {
				isRegisterToggled = true;
				mail.setVisible(true);
				passwordVerify.setVisible(true);
				registerToggleButton.setText("Already got an account!");
				sendButton.setText("Register");
			}
		}
	}

	public String getUser() {
		return nameField.getText();
	}

	public void setRegisterMode(boolean b) {
		this.passwordVerify.setVisible(b);
		this.mail.setVisible(b);
	}

	public String getPassword() {
		return password.getText();
	}

	public String getPasswordVerify() {
		return passwordVerify.getText();
	}

	public void setErrorFeedbackmessage(String message) {
		this.callbackValue.setText(message);
	}

	public String getMail() {
		return this.mail.getText();
	}

	public FocusWidget getSendButton() {
		return sendButton;
	}
	
	public boolean isRegisterToggled() {
		return isRegisterToggled;
	}

}
