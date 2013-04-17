package com.reaper.shared;

import java.io.Serializable;
import java.util.Date;

public class Bet implements Serializable {
	private static final long serialVersionUID = 9198558772952511345L;
	public long bettingDeadline; //unix time 8 byte
	public String leftTeam; // 32 ändras inte
	public String rightTeam; // 32 ändras inte
	public String tournamentName; // 128 ändras inte
	public Tag tag; // 32 ändras inte
	public short bestOf; // 1 ändras inte
	public short leftPool; // 1 ändras hela fucking tiden
	public short rightPool; // 1 ändras hela fucking tiden
	public short leftScore; // 1 ändras ibland
	public short rightScore; // 1 ändras ibland
	public String streamHTMLLink; // 128 ändras inte
									// tot:
									// 320 byte
									// 4 byte

	public Bet(String leftTeam, String rightTeam, String tournamentName,
			Tag tag, short bestOf, short leftPool, short rightPool,
			short leftScore, short rightScore, String streamHTMLLink) {
		this.leftTeam = leftTeam;
		this.rightTeam = rightTeam;
		this.tournamentName = tournamentName;
		this.tag = tag;
		this.bestOf = bestOf;
		this.leftPool = leftPool;
		this.rightPool = rightPool; 
		this.leftScore = leftScore; 
		this.rightScore = rightScore; 
		this.streamHTMLLink = streamHTMLLink;
	}

	public Bet() {
	}
}
