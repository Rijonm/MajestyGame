package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {
	public ArrayList<EnumCard> Brewer;
	public ArrayList<EnumCard> Defense;
	public ArrayList<EnumCard> Farmer;
	public ArrayList<EnumCard> Queen;
	public ArrayList<EnumCard> Soldier;
	public ArrayList<EnumCard> Witch;
	public ArrayList<EnumCard> Tavern;
	
	public ArrayList<EnumCard> getTavern() {
		return Tavern;
	}
	public void setTavern(ArrayList<EnumCard> tavern) {
		Tavern = tavern;
	}
	public ArrayList<EnumCard> getBrewer() {
		return Brewer;
	}
	public void setBrewer(ArrayList<EnumCard> brewer) {
		Brewer = brewer;
	}
	public ArrayList<EnumCard> getDefense() {
		return Defense;
	}
	public void setDefense(ArrayList<EnumCard> defense) {
		Defense = defense;
	}
	public ArrayList<EnumCard> getFarmer() {
		return Farmer;
	}
	public void setFarmer(ArrayList<EnumCard> farmer) {
		Farmer = farmer;
	}
	public ArrayList<EnumCard> getQueen() {
		return Queen;
	}
	public void setQueen(ArrayList<EnumCard> queen) {
		Queen = queen;
	}
	public ArrayList<EnumCard> getSoldier() {
		return Soldier;
	}
	public void setSoldier(ArrayList<EnumCard> soldier) {
		Soldier = soldier;
	}
	public ArrayList<EnumCard> getWitch() {
		return Witch;
	}
	public void setWitch(ArrayList<EnumCard> witch) {
		Witch = witch;
	}
	
	
}
