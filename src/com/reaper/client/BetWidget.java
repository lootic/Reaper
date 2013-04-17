package com.reaper.client;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.reaper.shared.Bet;

public class BetWidget extends FlexTable {

	BetWidget(Bet bet) {
	}
	
	private void constructorMethodA(Bet bet) {
		FlexCellFormatter cell = this.getFlexCellFormatter();
		cell.setColSpan(2, 0, 3);
		Label label;
		

		label =  new HTML(bet.tournamentName+", <a href=\""+bet.streamHTMLLink+"\" target=\"_blank\">stream</a>");
		setWidget(2, 0, label);
		
		label = new Label(bet.tag.toString());
		label.setStyleName("betTag");
		setWidget(1, 0, label);
		
		label = new Label("Best of " + bet.bestOf);
		label.setStyleName("bestOf");
		setWidget(1, 2, label);
		
		label =  new HTML("");
		setWidget(0, 2, label);
		
		label = new Label(bet.leftTeam);
		setWidget(3, 0, label);
		
		setText(3,1,"-");
		
		label = new Label(bet.rightTeam);
		setWidget(3, 2, label);
		
		label = new Label(bet.leftPool + "%");
		setWidget(4, 0, label);
		
		label = new Label(bet.rightPool + "%");
		setWidget(4, 2, label);
		
		
		setWidget(5, 0, new Button("Bet on "+ bet.leftTeam));
		setWidget(5, 2, new Button("Bet on "+ bet.rightTeam));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		this.setStyleName("bet");
	}
}
