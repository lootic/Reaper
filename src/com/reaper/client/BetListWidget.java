package com.reaper.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.reaper.shared.Bet;

public class BetListWidget extends FlexTable {
	BetListWidget() {
	}
	
	public void add(Bet bet) {
		constructorMethodB(bet);
	}
	
	private void constructorMethodB(Bet bet){
		int row = getRowCount();
		FlexCellFormatter cell = this.getFlexCellFormatter();
		Label label;
		
		label = new HTML(bet.leftTeam +" ("+ bet.leftPool +  "%) vs. " + bet.rightTeam +" ("+ bet.rightPool + "%)");
		setWidget(row, 1, label);
		
		label = new HTML(bet.tournamentName);
		setWidget(row, 2, label);
		
		label = new HTML(bet.tag.toString());
		setWidget(row, 3, label);
		

		label = new TimeLeftWidget(bet.bettingDeadline);
		setWidget(row, 4, label);
		
	}
	
	private void constructorMethodA(Bet bet){

		int row = getRowCount();
		FlexCellFormatter cell = this.getFlexCellFormatter();
		cell.setColSpan(2, 0, 3);
		Label label;
		
		label =  new HTML(bet.tournamentName+", <a href=\""+bet.streamHTMLLink+"\" target=\"_blank\">stream</a>");
		setWidget(row, 4, label);
		
		label = new Label(bet.tag.toString());
		label.setStyleName("betTag");
		setWidget(row, 5, label);
		
		label = new Label(bet.leftTeam);
		setWidget(row, 1, label);
		
		label = new Label(bet.rightTeam);
		setWidget(row, 2, label);
		
		label = new Label(bet.leftPool + "% :" + bet.rightPool + "%");
		setWidget(row, 3, label);
		
		setWidget(row, 0, new Button("bet"));
	}

}
