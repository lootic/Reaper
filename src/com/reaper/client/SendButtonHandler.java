package com.reaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class SendButtonHandler implements ClickHandler, KeyUpHandler {
	Reaper mediator;

	public SendButtonHandler(Reaper mediator) {
		this.mediator = mediator;
	}

	public void onClick(ClickEvent event) {
		mediator.send();
	}

	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			mediator.send();
		}
	}
}
