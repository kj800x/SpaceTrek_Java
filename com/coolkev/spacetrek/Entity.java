package com.coolkev.spacetrek;

/** This is an abstract class that all other entities are derived from.
 * @author Kevin Johnson**/
abstract class Entity {
	
	/** Age states how many seconds this entity has been alive **/
	int Age = 0;
	
	boolean FullSecond = true;
	
	/** Stage states what part of it's sequence it is in. **/
	int Stage = 0;
	
	boolean Ruins;
	
	World myWorld;
	
	/** If Dead is ever set to true, this object will be killed next second **/
	boolean Dead = false;
	
	/** If Ruins is true, this object will leave imprints on the world when it dies. Use this for bombs and the such. **/
	//boolean Ruins;// = false;

	/** This specifies where the entity was started **/
	int startX;
	/** This specifies where the entity was started **/
	int startY;
	
	/** This specifies where the render should be overlaid **/
	int topleftX;
	/** This specifies where the render should be overlaid **/
	int topleftY;
	
	/** This abstract method will return a Schematic (based on the State) that will be overlaid to the world, with top-left at this.topleftX and this.topleftY. The character '\0' will be treated as transparent. **/
	abstract Schematic Render();
	
	/** This abstract method should check this.Age to see if it meets certain requirements, then change this.Stage respectively. **/
	abstract void CheckForStageChange();
	
	boolean IsAt(int x, int y) {
		Schematic Render = Render();
		if (x>=Render.TopLeftX && y>=Render.TopLeftY && x<Render.TopLeftX+Render.LengthX && y<Render.TopLeftY+Render.LengthY){
			return Render.SchemoTiles[x - Render.TopLeftX][y - Render.TopLeftY].Character != '\n';
		}
		else {
			return false;
		}
	}
	
	/** This method is called every second and updates the state of the entity. **/
	void Update(){
		Age++;
		CheckForStageChange();
	}

	boolean DoesRuin(){
		return this.Ruins;
	}
}