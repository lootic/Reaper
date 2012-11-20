package com.reaper.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * EXAMPLE RootPanel.get().add(new EmbeddedVideoWidget(
 * "http://www.twitch.tv/widgets/live_embed_player.swf?channel=siglemic"));
 * 
 * @author lootic
 * 
 */
public class EmbeddedVideoWidget extends HTML {
	private int height;
	private int width;
	private String streamURL;

	public EmbeddedVideoWidget(String streamURL) {
		this(streamURL, 640, 480);
	}

	public EmbeddedVideoWidget(String streamURL, int width, int height) {
		super("<object type=\"application/x-shockwave-flash\" height=\""
				+ height + "\" width=\"" + width + "\" data=\"" + streamURL
				+ "\"/>");
		this.streamURL = streamURL;
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setDimensions(int width, int height) {
		this.height = height;
		this.width = width;
		updateHTML();
	}
	
	public void setStreamURL(String streamURL) {
		this.streamURL = streamURL;
		updateHTML();
	}
	
	private void updateHTML() {
		this.setHTML("<object type=\"application/x-shockwave-flash\" height=\""
				+ height + "\" width=\"" + width + "\" data=\"" + streamURL
				+ "\"/>");
	}
}
