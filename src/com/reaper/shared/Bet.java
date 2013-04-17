package com.reaper.shared;

import java.io.Serializable;
import java.util.Date;

public class Bet implements Serializable {
	private static final long serialVersionUID = 9198558772952511345L;
	public long bettingDeadline; //unix time 8 byte
	public String leftTeam; // 32 �ndras inte
	public String rightTeam; // 32 �ndras inte
	public String tournamentName; // 128 �ndras inte
	public Tag tag; // 32 �ndras inte
	public short bestOf; // 1 �ndras inte
	public short leftPool; // 1 �ndras hela fucking tiden
	public short rightPool; // 1 �ndras hela fucking tiden
	public short leftScore; // 1 �ndras ibland
	public short rightScore; // 1 �ndras ibland
	public String streamHTMLLink; // 128 �ndras inte
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
