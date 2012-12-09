package com.reaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LogoutButtonHandler implements ClickHandler{
	private Reaper mediator;
	
	public LogoutButtonHandler(Reaper mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		mediator.logout();
	}
}
