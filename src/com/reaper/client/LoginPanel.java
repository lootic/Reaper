package com.reaper.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

public class LoginPanel extends PopupPanel {
	public LoginPanel() {
		setStyleName("loginPanel");
		
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();
			}
		});
	}
	
	public void show() {
		super.show();
		resize();
	}

	public void resize() {
		if (isShowing()) {
			setPopupPosition(Window.getClientWidth()-getOffsetWidth(), 40);
		}
	}
}
