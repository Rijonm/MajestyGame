package CommonClasses;

import java.util.ArrayList;

public class InformationFromServerMessage extends Message{

	private ArrayList<Integer> openCards;
	private int[] meeples;
	
	public InformationFromServerMessage(ArrayList<Integer> openCards) {
		super(MessageType.InformationFromServerMessage);
		
		this.openCards = openCards;
		this.meeples = meeples;
	}

}
