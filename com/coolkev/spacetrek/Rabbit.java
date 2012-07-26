package com.coolkev.spacetrek;

import java.awt.Color;
import java.lang.Math;

class Rabbit extends Entity {

	Color RabbitColor;

	public Rabbit(int X, int Y, World TheWorld) {
		startX = X;
		startY = Y;
		topleftX = X;
		topleftY = Y;
		myWorld = TheWorld;
		double RabbitColorNumber = Math.random()*10;
		if (RabbitColorNumber < 3){
			RabbitColor = new Color(242,183,107);
		}
		else if (RabbitColorNumber < 6){
			RabbitColor = new Color(214,127,13);
		}
		else if (RabbitColorNumber < 10){
			RabbitColor = new Color(196,144,77);
		}
		super.Ruins = false;
	}

	Schematic Render() {
		Schematic Output = new Schematic(1,1);
		Output.TopLeftX = topleftX;
		Output.TopLeftY = topleftY;
		Output.SchemoTiles[0][0].Character = 'R';
		Output.SchemoTiles[0][0].fgColor   = RabbitColor;
		Output.SchemoTiles[0][0].Collision = false;
		return Output;
	}

	void CheckForStageChange() {
		/*
		int ClosestX = 0;
		int ClosestY = 0;
		int ClosestDistance = 10000;

		for (int index = 0; index < myWorld.Entities.size(); index++){
			if (myWorld.Entities.get(index) instanceof Rabbit){
				if ((myWorld.Entities.get(index).topleftX != topleftX) && (myWorld.Entities.get(index).topleftY != topleftY)){
					if (ClosestDistance > (Math.hypot(myWorld.Entities.get(index).topleftX-topleftX, myWorld.Entities.get(index).topleftY-topleftY)));
					ClosestX = myWorld.Entities.get(index).topleftX;
					ClosestY = myWorld.Entities.get(index).topleftY;
					ClosestDistance = (int) Math.hypot(myWorld.Entities.get(index).topleftX-topleftX, myWorld.Entities.get(index).topleftY-topleftY);
				}
			}
		}
		 */	
		// I now have the ClosestX and ClosestY of the Closest Rabbit. If ClosestDistance == 10000 then there is no other Rabbit.
		// TODO Complete this function
		/*if (ClosestDistance == 10000){
			//RANDOM MOVEMENT
		} else if (Math.random()*10 > 7){
			// Pass
		}else if (ClosestX > topleftX){
			if (myWorld.canIMoveThere(topleftX+1, topleftY)){
				topleftX++;
			}
		}else if (ClosestX < topleftX){
			if (myWorld.canIMoveThere(topleftX-1, topleftY)){
				topleftX--;
			}
		}else if (ClosestY > topleftY){
			if (myWorld.canIMoveThere(topleftX, topleftY+1)){
				topleftY++;
			}
		}else if (ClosestY < topleftY){
			if (myWorld.canIMoveThere(topleftX, topleftY-1)){
				topleftY--;
			}
		}
		if      (topleftX > startX && topleftY > startY){
			double RandomNumber = Math.random()*10;
			if (RandomNumber < 2.5){
				topleftX++;
			}
			else ifRuins (RandomNumber < 5){
				topleftY++;
			}
			else if (RandomNumber < 7.5){
				topleftX--;
			}
			else if (RandomNumber < 10){
				topleftY--;
			}
		}
		else if (topleftX < startX && topleftY > startY){

		}
		else if (topleftX > startX && topleftY < startY){

		}
		else if (topleftX < startX && topleftY < startY){

		}
		else{*/
		if (myWorld.getPoint(topleftX, topleftY).PermCharacter == ':' || myWorld.getPoint(topleftX, topleftY).PermCharacter == ';'){
			myWorld.setPointForever(topleftX, topleftY, '.', false, Color.ORANGE);
		}
		double RandomNumber = Math.random()*10;
		if (RandomNumber < 2.5){
			if (myWorld.canIMoveThere(topleftX+1, topleftY)){
				topleftX++;
			}
		}
		else if (RandomNumber < 5){
			if (myWorld.canIMoveThere(topleftX, topleftY+1)){
				topleftY++;
			}
		}
		else if (RandomNumber < 7.5){
			if (myWorld.canIMoveThere(topleftX-1, topleftY)){
				topleftX--;
			}
		}
		else if (RandomNumber < 10){
			if (myWorld.canIMoveThere(topleftX, topleftY-1)){
				topleftY--;
			}
		}

		if (Age > 20){
			Ruins = false;
			Dead = true;
		}
		//}
}
}