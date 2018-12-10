package Model;

import java.util.ArrayList;


public class Hand {
	
	int[] handSize = new int[8];
	int[] hand = new int[8];
	
	
	public static ArrayList<CardBrewer> Brewer = new ArrayList <>();
	public static ArrayList<CardDefense> Defense = new ArrayList <>();
	public static ArrayList<CardFarmer> Farmer = new ArrayList <>();
	public static ArrayList<CardQueen> Queen = new ArrayList <>();
	public static ArrayList<CardSoldier> Soldier = new ArrayList <>();
	public static ArrayList<CardWitch> Witch = new ArrayList <>();
	public static ArrayList<CardTavern> Tavern = new ArrayList <>();
	public static ArrayList<Card> Lazarett = new ArrayList <>();
	
	public Hand() {
		for(int i = 0; i<hand.length; i++) { // Punktezahl 0,0,0,0,0,0,0,0
			hand[i] = 0;
		}
	}
	
	public int[] getPlayerCards() {
	handSize[0] = Brewer.size();
	handSize[1] = Defense.size();
	handSize[2] = Farmer.size();
	handSize[3] = Queen.size();
	handSize[4] = Soldier.size();
	handSize[5] = Witch.size();
	handSize[6]	= Tavern.size();
	handSize[7] = Lazarett.size();	
	return handSize;
	}
	
}