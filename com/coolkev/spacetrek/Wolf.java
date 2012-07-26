package com.coolkev.spacetrek;

import java.awt.Color;
import java.util.ArrayList;

class Wolf extends Entity {
	
	private Color WolfColor;
	
	char Movement;
	
	public Wolf(int X, int Y, World TheWorld) {
		startX = X;
		startY = Y;
		topleftX = X;
		topleftY = Y;
		myWorld = TheWorld;
		double WolfColorNumber = Math.random()*10;
		if (WolfColorNumber < 3){
			WolfColor = new Color(69,64,59);
		}
		else if (WolfColorNumber < 6){
			WolfColor = new Color(128,122,116);
		}
		else if (WolfColorNumber < 10){
			WolfColor = new Color(110,98,84);
		}
		
		double RandomNumber = Math.random()*10;
		
		if (RandomNumber < 2.5){
			Movement = 'U';	
		}
		else if (RandomNumber < 5.0){
			Movement = 'L';
		}
		else if (RandomNumber < 7.5){
			Movement = 'R';
		}
		else if (RandomNumber < 10){
			Movement = 'D';
		}
		
		super.Ruins = false;
	}
	
	Schematic Render() {
		Schematic Output = new Schematic(1,1);
		Output.TopLeftX = topleftX;
		Output.TopLeftY = topleftY;
		Output.SchemoTiles[0][0].Character = 'w';
		Output.SchemoTiles[0][0].fgColor   = WolfColor;
		Output.SchemoTiles[0][0].Collision = false;
		return Output;
	}

	void CheckForStageChange() {
		if (Age == 60){
			Dead = true;
		}else if (Movement == 'D'){
			if (myWorld.canIMoveThere(topleftX+1, topleftY)){
				topleftX++;
			}
		}
		else if (Movement == 'R'){
			if (myWorld.canIMoveThere(topleftX, topleftY+1)){
				topleftY++;
			}
		}
		else if (Movement == 'L'){
			if (myWorld.canIMoveThere(topleftX-1, topleftY)){
				topleftX--;
			}
		}
		else if (Movement == 'U'){
			if (myWorld.canIMoveThere(topleftX, topleftY-1)){
				topleftY--;
			}
		}
		ArrayList<Entity> List = myWorld.GetEntitiesAt(topleftX, topleftY);
		for (int index=0;index<List.size();index++){
			if (List.get(index) instanceof Rabbit || List.get(index) instanceof LittleRabbit){
				List.get(index).Dead = true;
			}
		}
	}

}
