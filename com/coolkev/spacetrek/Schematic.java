package com.coolkev.spacetrek;

import java.io.Serializable;

/** This class details how to pass parts of the world between functions. '\0' is used for transparency. Sometimes '\t' is used for outofbounds characters, especially in the editor.
 * @author Kevin Johnson**/
class Schematic implements Serializable {
	
	private static final long serialVersionUID = 2468052970283653987L;
	
	/** These fields define where the schematic goes. **/
	int TopLeftX;
	/** These fields define where the schematic goes. **/
	int TopLeftY;
	/** These fields define the size of the schematic. **/
	int LengthX;
	/** These fields define the size of the schematic. **/
	int LengthY;
	/** This is the character array for the schematic, aka where ALL the data is. **/
	Tile[][] SchemoTiles;

	/** This constructor initializes the schematic with full transparency and the correct length and width.
	 * @param width (int)  The width of the Schematic.
	 * @param length (int) The length of the Schematic.**/
	public Schematic(int width, int length){
		LengthX = width;
		LengthY = length;
		SchemoTiles = new Tile[width][length];
		for (int x=0; x<width; x++){
			for (int y=0; y<length; y++){
				SchemoTiles[x][y] = new Tile('\0');
			}
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		for (int x=0; x<LengthX; x++){
			for (int y=0; y<LengthY; y++){
				result.append(SchemoTiles[y][x].Character);
			}
			result.append(NEW_LINE);
		}
		return result.toString();

	}


	boolean IsInsideBounds(int x, int y) {
		if (x>=0 && y>=0 && x<LengthX &&  y<LengthY){
			return true;
		}
		else {
			return false;
		}
	}

	void AddLeftColumn(){
		Tile[][] NewSchemoTiles = new Tile[LengthX+1][LengthY];
		for (int y=0; y<LengthY; y++){
			NewSchemoTiles[0][y] = new Tile();
		}
		for (int x=1; x<LengthX+1; x++){
			for (int y=0; y<LengthY; y++){
				NewSchemoTiles[x][y] = SchemoTiles[x-1][y];
			}
		}
		SchemoTiles = NewSchemoTiles;
		LengthX = LengthX + 1;
	}
	void AddRightColumn(){
		Tile[][] NewSchemoTiles = new Tile[LengthX+1][LengthY];
		for (int x=0; x<LengthX; x++){
			for (int y=0; y<LengthY; y++){
				NewSchemoTiles[x][y] = SchemoTiles[x][y];
			}
		}
		for (int y=0; y<LengthY; y++){
			NewSchemoTiles[LengthX][y] = new Tile();
		}
		SchemoTiles = NewSchemoTiles;
		LengthX = LengthX + 1;
	}
	void AddTopRow(){
		Tile[][] NewSchemoTiles = new Tile[LengthX][LengthY+1];
		for (int x=0; x<LengthX; x++){
			NewSchemoTiles[x][0] = new Tile();
		}
		for (int x=0; x<LengthX; x++){
			for (int y=1; y<LengthY+1; y++){
				NewSchemoTiles[x][y] = SchemoTiles[x][y-1];
			}
		}
		SchemoTiles = NewSchemoTiles;
		LengthY = LengthY + 1;
	}
	void AddBottomRow(){
		Tile[][] NewSchemoTiles = new Tile[LengthX][LengthY+1];
		for (int x=0; x<LengthX; x++){
			for (int y=0; y<LengthY; y++){
				NewSchemoTiles[x][y] = SchemoTiles[x][y];
			}
		}
		for (int x=0; x<LengthX; x++){
			NewSchemoTiles[x][LengthY] = new Tile();
		}
		SchemoTiles = NewSchemoTiles;
		LengthY = LengthY + 1;
	}

	//TODO check the removes and makesure that LengthX and LengthY are not too small afterwards = 0?
	boolean RemoveLeftColumn(){
		if (!(LengthX-1 == 0)){
			Tile[][] NewSchemoTiles = new Tile[LengthX-1][LengthY];
			for (int x=0; x<LengthX-1; x++){
				for (int y=0; y<LengthY; y++){
					NewSchemoTiles[x][y] = SchemoTiles[x+1][y];
				}
			}
			SchemoTiles = NewSchemoTiles;
			LengthX = LengthX - 1;
			return true;
		} else {
			return false;
		}
	}
	boolean RemoveRightColumn(){
		if (!(LengthX-1 == 0)){
			Tile[][] NewSchemoTiles = new Tile[LengthX-1][LengthY];
			for (int x=0; x<LengthX-1; x++){
				for (int y=0; y<LengthY; y++){
					NewSchemoTiles[x][y] = SchemoTiles[x][y];
				}
			}
			SchemoTiles = NewSchemoTiles;
			LengthX = LengthX - 1;
			return true;
		} else {
			return false;
		}
	}
	boolean RemoveTopRow(){
		if (!(LengthY-1 == 0)){
			Tile[][] NewSchemoTiles = new Tile[LengthX][LengthY-1];
			for (int x=0; x<LengthX; x++){
				for (int y=0; y<LengthY-1; y++){
					NewSchemoTiles[x][y] = SchemoTiles[x][y+1];
				}
			}
			SchemoTiles = NewSchemoTiles;
			LengthY = LengthY - 1;
			return true;
		} else {
			return false;
		}
	}
	boolean RemoveBottomRow(){
		if (!(LengthY-1 == 0)){
			Tile[][] NewSchemoTiles = new Tile[LengthX][LengthY-1];
			for (int x=0; x<LengthX; x++){
				for (int y=0; y<LengthY-1; y++){
					NewSchemoTiles[x][y] = SchemoTiles[x][y];
				}
			}
			SchemoTiles = NewSchemoTiles;
			LengthY = LengthY - 1;
			return true;
		} else {
			return false;
		}
	}
}