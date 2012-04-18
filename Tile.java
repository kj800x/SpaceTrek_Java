import java.awt.Color;

class Tile {
	
	public Color fgColor;
	public boolean Collision;
	public char Character;
	public char PermCharacter;
	public boolean PermCollision;
	public Color PermfgColor;
	
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