package com.reaper.client;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public class NumberTextfield extends Textfield{

	NumberTextfield(String text) {
		super(text);
		this.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == 0) {
					return;
				} else if (!(event.getCharCode() > 47 && event.getCharCode() < 58)) {
					event.preventDefault();
				}
			}
		});
	}

}
