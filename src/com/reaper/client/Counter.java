package com.reaper.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

public class Counter extends FlexTable {
	private NumberTextfield numberTextfield;

	public Counter(String text) {
		numberTextfield = new NumberTextfield(text);
		Button decrease = new Button("-");
		Button increase = new Button("+");
		setWidget(0, 0, decrease);
		setWidget(0, 1, numberTextfield);
		setWidget(0, 2, increase);

		increase.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					numberTextfield.setText(Integer.toString((Integer
							.parseInt(numberTextfield.getText()) + 1)));
				} catch (Exception e) {
					numberTextfield.setStyleName("normalTextfield");
					numberTextfield.setText("1");
				}
			}
		});

		decrease.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					numberTextfield.setText(Integer.toString((Integer
							.parseInt(numberTextfield.getText()) - 1)));
				} catch (Exception e) {
					numberTextfield.setStyleName("normalTextfield");
					numberTextfield.setText("-1");
				}
			}
		});
	}
}
