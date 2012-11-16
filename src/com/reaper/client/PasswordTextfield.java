package com.reaper.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class PasswordTextfield extends Textfield {

	private StringBuilder sb = new StringBuilder();
	private int counter = 0;

	PasswordTextfield(String text) {
		super(text);
		this.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub

			//	setText(Integer.toString(counter));
			}
		});
		this.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
			//	setText(Integer.toString(counter));
			}
		});
		this.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
			//	setText(Integer.toString(counter));
				setText("");
			}
		});
		this.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
			//	setText(Integer.toString(counter));
			}
		});

		this.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				++counter;
				StringBuilder passwordFeedback = new StringBuilder();
				for (int i = 0; i < counter; ++i) {
					passwordFeedback.append('☻');
				}
				setText(passwordFeedback.toString());
//				sb.append(getText().charAt(getText().length()-1));
			}
		});
	}

	@Override
	public String getText() {
		return sb.toString();
	}
}
