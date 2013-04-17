package com.reaper.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class TimeLeftWidget extends Label{
	private long targetDate;
	
	TimeLeftWidget(long bettingDeadline) {
		this.targetDate = bettingDeadline;
		Timer t = new Timer(){
			@Override
			public void run() {
				timeLeft();
			}
		};
		t.scheduleRepeating(2000);
	}

	private String timeLeft() {
		long unixTimeLeft = (targetDate - System.currentTimeMillis())/1000L;
		this.setText(Long.toString(unixTimeLeft));
		return Long.toString(unixTimeLeft);
	}
}
