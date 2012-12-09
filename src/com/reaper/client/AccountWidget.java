package com.reaper.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class AccountWidget extends FlexTable {
	AccountWidget() {
		Label activeCreditCard = new Label(
				"Active Credit Card:\n **** **** **** 3621");
		Button logoutButton = new Button("Log out");
		Button settingsButton = new Button("Settings");
		Button depositButton = new Button("Deposit");
		Button withdrawButton = new Button("Withdraw");
		NumberTextfield amount = new NumberTextfield("Transaction amount");
		FlexCellFormatter cellFormatter = getFlexCellFormatter();

		setWidget(0, 0, activeCreditCard);
		setWidget(1, 0, amount);
		setWidget(2, 0, withdrawButton);
		setWidget(2, 1, depositButton);
		setWidget(3, 0, settingsButton);
		setWidget(3, 1, logoutButton);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setColSpan(1, 0, 2);
		cellFormatter.setStyleName(3, 0, "userDropDownLastRow");
		cellFormatter.setStyleName(3, 1, "userDropDownLastRow");
	}
}
