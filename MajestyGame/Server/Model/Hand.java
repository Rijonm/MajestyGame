package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.smartcardio.Card;

public class Hand implements Serializable {
	
	//Not EnumCard, it should be something else, Hand represent players Building Cards with choosen player Cards
	
	public static ArrayList<Cards> Brewer = new ArrayList <Cards>();
	public static ArrayList<Cards> Defense = new ArrayList <Cards>();
	public static ArrayList<Cards> Farmer = new ArrayList <Cards>();
	public static ArrayList<Cards> Queen = new ArrayList <Cards>();
	public static ArrayList<Cards> Soldier = new ArrayList <Cards>();
	public static ArrayList<Cards> Witch = new ArrayList <Cards>();
	public static ArrayList<Cards> Tavern = new ArrayList <Cards>();
	
	Cards CardBrewer = new CardBrewer(1, "Brauer", 5, 5);
	
	Brewer.add(1, "Brauer", 5, 5);
	Brewer.add(CardBrewer);
	
}
