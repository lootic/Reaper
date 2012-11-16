package com.reaper.shared;

import java.io.Serializable;

public enum Tag implements Serializable{
	STARCRAFT2(0), DOTA2(1);
	private final int index;

	Tag(int index) {
		this.index = index;
	}

	public int index() {
		return index;
	}
	
	@Override
	public String toString() {
		switch(index){
		case 0:
			return "Starcraft 2";
		case 1:
			return "DOTA 2";
		default:
			return "";
		}
	}
}
