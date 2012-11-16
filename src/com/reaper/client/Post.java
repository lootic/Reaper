package com.reaper.client;

import com.google.gwt.user.client.ui.HTML;

public class Post extends HTML{
	private static String start = "<div>";
	private static String end =  "</div>";
	Post() {
		this.setHTML("<div class=post></div>");
	//	this.setStyleName("post");
	}
	
	public Post(String content) {
		this.setHTML(start+content+end);this.setStyleName("post");
	}

	public void setUser(String username) {
	}
	
	public void setContent(String content){
		this.setHTML(start+content+end);
	}
}
