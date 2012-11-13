package com.reaper.client;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Textfield extends TextBox{
	private String emptyText;
	private boolean isEmpty = true;
	
	Textfield(String text) {
		this.emptyText = text;
		this.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if(getText().equals("")){
					isEmpty = true;
					setText(emptyText);
					setStyleName("emptyTextfield");
				}
			}
		});
		this.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if(isEmpty){
					setStyleName("normalTextfield");
					setText("");
					isEmpty = false;
				}
			}
		});
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		this.setText(emptyText);
		this.setStyleName("emptyTextfield");
	}	
}
