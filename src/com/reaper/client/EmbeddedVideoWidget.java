package com.reaper.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * EXAMPLE
 * RootPanel.get().add(new EmbeddedVideoWidget("http://www.twitch.tv/widgets/live_embed_player.swf?channel=siglemic"));
 * @author lootic
 *
 */
public class EmbeddedVideoWidget extends SimplePanel {
	private int height;
	private int width;
	private String streamURL;
	
	public EmbeddedVideoWidget(String streamURL) {
		this.streamURL = streamURL;
		updateWidget();
	}
	
	public EmbeddedVideoWidget(String streamURL,  int width,  int height) {
		this.streamURL = streamURL;
		this.width = width;
		this.height = height;
		updateWidget();
	}
	
	private void updateWidget(){
		this.clear();
		this.add(new HTML("<object type=\"application/x-shockwave-flash\" height=\""+ height +"\" width=\""+ width +"\" data=\"" 
						+ streamURL + "\"/>"));
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
		updateWidget();
	}
}
