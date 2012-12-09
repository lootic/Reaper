package com.reaper.client;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class PasswordTextfield extends Textfield {

	private StringBuilder sb = new StringBuilder();
	private boolean backspace = false;
	private boolean delete = false;

	PasswordTextfield(String text) {
		super(text);

		this.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == 8) {
					backspace = true;
				}
				if (event.getNativeKeyCode() == 46) {
					delete = true;
				}
			}
		});

		this.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == 8) {
					backspace = false;
				}
				if (event.getNativeKeyCode() == 46) {
					delete = false;
				}
			}
		});

		this.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				StringBuilder passwordFeedback = new StringBuilder();
				int cursorPosition = getCursorPos();
				int selection = getSelectionLength();

				if(selection > 0){
					sb.delete(cursorPosition, cursorPosition+selection);
				}
				if (event.getCharCode() != 0) {
					sb.append(event.getCharCode());
					++cursorPosition;
				} else if (backspace && sb.length() > 0 && selection == 0) {
					if (getCursorPos() != 0) {
						sb.deleteCharAt(getCursorPos() - 1);
					}
					--cursorPosition;
				} else if (delete && sb.length() > 0 && selection == 0) {
					sb.deleteCharAt(getCursorPos());
				} else {
					return;
				}

				event.preventDefault();
		
				for (int i = 0; i < sb.length(); ++i) {
					passwordFeedback.append('☻');
				}
				setText(passwordFeedback.toString());
				setCursorPos(cursorPosition);
			}
		});
	}

	@Override
	public String getText() {
		return sb.toString();
	}
}
