package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.smartcardio.Card;

public class Hand implements Serializable {
	
	//Not EnumCard, it should be something else, Hand represent players Building Cards with choosen player Cards
	
	public static ArrayList<Integer> Brewer = new ArrayList <Integer>();
	public static ArrayList<Integer> Defense = new ArrayList <Integer>();
	public static ArrayList<Integer> Farmer = new ArrayList <Integer>();
	public static ArrayList<Integer> Queen = new ArrayList <Integer>();
	public static ArrayList<Integer> Soldier = new ArrayList <Integer>();
	public static ArrayList<Integer> Witch = new ArrayList <Integer>();
	public static ArrayList<Integer> Tavern = new ArrayList <Integer>();
	

	
}
