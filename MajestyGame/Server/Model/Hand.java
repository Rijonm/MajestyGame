package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.smartcardio.Card;

public class Hand implements Serializable {
	
	//Not EnumCard, it should be something else, Hand represent players Building Cards with choosen player Cards
	
	public static ArrayList<Card> Brewer = new ArrayList <>();
	public static ArrayList<Card> Defense = new ArrayList <>();
	public static ArrayList<Card> Farmer = new ArrayList <>();
	public static ArrayList<Card> Queen = new ArrayList <>();
	public static ArrayList<Card> Soldier = new ArrayList <>();
	public static ArrayList<Card> Witch = new ArrayList <>();
	public static ArrayList<Card> Tavern = new ArrayList <>();
	
	public Hand() {
	//Brewer.add(cardBrewer);
	//Brewer.add();
	}
}
