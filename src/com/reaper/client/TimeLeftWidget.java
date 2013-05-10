package com.reaper.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class TimeLeftWidget extends Label {
	private long targetDate;

	TimeLeftWidget(long bettingDeadline) {
		this.targetDate = bettingDeadline;
		Timer t = new Timer() {
			@Override
			public void run() {
				timeLeft();
			}
		};
		t.scheduleRepeating(2000);
	}

	private String timeLeft() {
		StringBuilder deadline = new StringBuilder();
		long unixTimeLeft = (targetDate - System.currentTimeMillis()) / 1000L;

		long days = unixTimeLeft / 3600 * 24;
		if (days > 0) {
			deadline.append(Long.toString(days) + "d ");
			unixTimeLeft %= 3600*24;
		}

		long hours = unixTimeLeft / 3600;
		if (hours > 0) {
			deadline.append(Long.toString(hours) + "h ");
		}

		this.setText(Long.toString(hours) + "h");
		return Long.toString(unixTimeLeft);
	}
}
