package com.reaper.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Topic implements Serializable{
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
