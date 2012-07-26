package com.coolkev.spacetrek;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.Character;
import java.lang.Integer;

public class SchematicEditor extends Applet implements KeyListener {
	BufferedImage bf;
	Sprite Sprite = new Sprite();
	int SpriteState = 1;
	char MenuState = 'O';
	private static final long serialVersionUID = 1L;
	int currentX, currentY;

	Menu CurrentMenu = new Menu();

	public void init() {
		setBackground( Color.black );
		currentX = 0;
		currentY = 0;
		addKeyListener( this );
	}

	public void keyPressed(KeyEvent e) {       
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			currentX--;
		}
		else if (key == KeyEvent.VK_RIGHT) {
			currentX++;
		}
		else if (key == KeyEvent.VK_UP) {
			currentY--;
		}
		else if (key == KeyEvent.VK_DOWN) {
			currentY++;
		}
		repaint();
	}

	public void keyReleased( KeyEvent e ) { }

	public void keyTyped( KeyEvent e ) {
		switch (MenuState) {
		case 'C':
			if (Sprite.Schems.get(SpriteState).IsInsideBounds(currentX, currentY)) {
				Sprite.Schems.get(SpriteState).SchemoTiles[currentX][currentY].Character = e.getKeyChar();
			}
			MenuState = 'O';
			break;
		case 'O':
			if (e.getKeyChar() == 'a'){
				currentX--;
			}
			if (e.getKeyChar() == 'd'){
				currentX++;
			}
			if (e.getKeyChar() == 'w'){
				currentY--;
			}
			if (e.getKeyChar() == 's'){
				currentY++;
			}
			if (e.getKeyChar() == 'z'){
				Sprite.Save("SpaceTrekSave.sav");
			}
			if (e.getKeyChar() == 'x'){
				Sprite.Load("SpaceTrekSave.sav");
			}
			if (e.getKeyChar() == 'm'){
				if (SpriteState < Sprite.Schems.size()-1){
					SpriteState++;
				}
			}
			if (e.getKeyChar() == 'n'){
				if (SpriteState != 0){
					SpriteState--;
				}
			}
			if (e.getKeyChar() == 'c'){
				Sprite.Schems.add(new Schematic(20,20)); 
			}
			if (e.getKeyChar() == 'i'){
				if (Sprite.Schems.get(SpriteState).SchemoTiles[currentX][currentY].Collision){
					Sprite.Schems.get(SpriteState).SchemoTiles[currentX][currentY].Collision = false;
				} else{
					Sprite.Schems.get(SpriteState).SchemoTiles[currentX][currentY].Collision = true;
				}
			}
			if (e.getKeyChar() == 't'){
				Sprite.Schems.get(SpriteState).SchemoTiles[currentX][currentY].Character = '\0';
			}
			if (e.getKeyChar() == 'e'){
				MenuState = 'C'; 
			}
			if (e.getKeyChar() == 'q'){
				MenuState = 'E'; 
			}
			break;
		case 'E':
			if (e.getKeyChar() == 'a'){ //Add a column to the left side
				Sprite.Schems.get(SpriteState).AddLeftColumn();
				currentX++;
			}
			if (e.getKeyChar() == 'd'){ //Add a column to the right side
				Sprite.Schems.get(SpriteState).AddRightColumn();
			}
			if (e.getKeyChar() == 'w'){ //Add a row to the top side
				Sprite.Schems.get(SpriteState).AddTopRow();
				currentY++;
			}
			if (e.getKeyChar() == 's'){ //Add a row to the bottom side
				Sprite.Schems.get(SpriteState).AddBottomRow();
			}

			if (e.getKeyChar() == 'j'){ //Remove the left-most column
				boolean FunctionResult = Sprite.Schems.get(SpriteState).RemoveLeftColumn();
				if (FunctionResult){
					currentX--;
				}
			}
			if (e.getKeyChar() == 'l'){ //Remove the right-most column
				Sprite.Schems.get(SpriteState).RemoveRightColumn();
			}
			if (e.getKeyChar() == 'i'){ //Remove the top-most row
				boolean FunctionResult = Sprite.Schems.get(SpriteState).RemoveTopRow();
				if (FunctionResult){
					currentY--;
				}
			}
			if (e.getKeyChar() == 'k'){ //Remove the bottom-most row
				Sprite.Schems.get(SpriteState).RemoveBottomRow();
			}

			if (e.getKeyChar() == 'q'){ //Swap Back To Default
				MenuState = 'O';
			}
			break;
		}
		repaint();
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint( Graphics endg ){
		bf = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bf.getGraphics();

		int fontsize = 30;
		int viewwidthandhight = 20;
		g.setFont(new Font("Courier New", Font.PLAIN, fontsize));

		Schematic OutputData = new Schematic(viewwidthandhight, viewwidthandhight);
		for (int tempX = 0; tempX < viewwidthandhight; tempX++){
			for (int tempY = 0; tempY < viewwidthandhight; tempY++){
				// Check to see if tempx and tempy are within the posible ranges for the absolute coordinates (Sprite.Schems.get(SpriteState).SchemoTiles)
				int absX = currentX + tempX - viewwidthandhight/2;
				int absY = currentY + tempY - viewwidthandhight/2;
				if (absX>=0 && absX<Sprite.Schems.get(SpriteState).LengthX && absY>=0 && absY<Sprite.Schems.get(SpriteState).LengthY){
					OutputData.SchemoTiles[tempX][tempY].Character = Sprite.Schems.get(SpriteState).SchemoTiles[absX][absY].Character;
					OutputData.SchemoTiles[tempX][tempY].fgColor   = Sprite.Schems.get(SpriteState).SchemoTiles[absX][absY].fgColor;
					OutputData.SchemoTiles[tempX][tempY].Collision = Sprite.Schems.get(SpriteState).SchemoTiles[absX][absY].Collision;
				}
				else{
					//Fill the schematic in places that are not in the sprite. 
					OutputData.SchemoTiles[tempX][tempY].Character = '\t';
					OutputData.SchemoTiles[tempX][tempY].fgColor   = Color.white;
					OutputData.SchemoTiles[tempX][tempY].Collision = true;
				}
			}
		}
		for (int localx = 0; localx < OutputData.LengthX; localx++){
			for (int localy = 0; localy < OutputData.LengthY; localy++){
				if (OutputData.SchemoTiles[localx][localy].Collision){
					if (OutputData.SchemoTiles[localx][localy].Character == '\t'){
						g.setColor(Color.orange);
					}else {
						g.setColor(Color.red);
					}
					g.fillRect(fontsize*(1+localx), fontsize*(1+localy), fontsize, fontsize);
				}
				g.setColor(OutputData.SchemoTiles[localx][localy].fgColor);
				if (OutputData.SchemoTiles[localx][localy].Character=='\t'){
					g.setColor(Color.white);
					g.drawString(">", fontsize*(1+localx), fontsize*(2+localy));
					g.drawString("<", fontsize*(1+localx), fontsize*(2+localy));
				} else if (OutputData.SchemoTiles[localx][localy].Character=='\0'){
					g.setColor(Color.blue);
					g.drawString("+", fontsize*(1+localx), fontsize*(2+localy));
					g.drawString("O", fontsize*(1+localx), fontsize*(2+localy));
				} else {
					g.drawString(Character.toString(OutputData.SchemoTiles[localx][localy].Character), fontsize*(1+localx), fontsize*(2+localy));	
				}
			}
		}
		g.setColor(Color.green);
		g.drawRect(((viewwidthandhight/2)+1)*fontsize, ((viewwidthandhight/2)+1)*fontsize, fontsize, fontsize);

		if (MenuState == 'A'){

		}

		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.PLAIN, 16));
		g.drawString("At: " + new Integer(currentX).toString() + "," + new Integer(currentY).toString(), 1 , 20);
		g.drawString("Mode: " + new Character(MenuState).toString(), 1 , 660);

		endg.drawImage(bf, 0, 0, null);
	}
}