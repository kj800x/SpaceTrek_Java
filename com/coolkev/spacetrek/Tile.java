package com.coolkev.spacetrek;

import java.awt.Color;
import java.io.Serializable;

class Tile implements Serializable{
	
	private static final long serialVersionUID = -8636862335980233248L;
	
	public Color fgColor;
	public boolean Collision;
	public char Character;
	public char PermCharacter;
	public boolean PermCollision;
	public Color PermfgColor;

	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(Character);
		result.append(" ");
		if (Collision){
			result.append("X");	
		} else {
			result.append(".");
		}
		result.append(" ");
		result.append(fgColor);

		return result.toString();
	}

	public Tile(){
		Character = '\0';
		PermCharacter = '\0';
		PermfgColor = Color.white;
		Collision = false;
		fgColor = Color.white;
	}	

	public Tile(char NCharacter){
		Character = NCharacter;
		PermCharacter = NCharacter;
		PermfgColor = Color.white;
		Collision = false;
		fgColor = Color.white;
	}

	public Tile(char NCharacter, boolean NCollision){
		Character = NCharacter;
		PermCharacter = NCharacter;
		PermfgColor = Color.white;
		Collision = NCollision;
		fgColor = Color.white;
	}

	public Tile(char NCharacter, Color NColor){
		Character = NCharacter;
		PermCharacter = NCharacter;
		PermfgColor = NColor;
		Collision = false;
		fgColor = NColor;
	}

	public Tile(char NCharacter, boolean NCollision, Color NColor){
		Character = NCharacter;
		PermCharacter = NCharacter;
		PermfgColor = NColor;
		Collision = NCollision;
		fgColor = NColor;
	}
}