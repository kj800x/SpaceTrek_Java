package com.coolkev.spacetrek;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import java.lang.Character;

class RemindTask extends TimerTask {
	World myWorld;
	SpaceTrek myGame;
	public void run() {
		/*if ((Math.random() * 10)>7){
			myWorld.Entities.add(new LittleRabbit( (int)(Math.random() * 40), (int)(Math.random() * 40), myWorld));
		}*/
		int EntIndex=0;
		for (EntIndex=0; EntIndex < myWorld.Entities.size(); EntIndex++){
			myWorld.Entities.get(EntIndex).Update();
			if (myWorld.Entities.get(EntIndex).Dead){
				if (myWorld.Entities.get(EntIndex).DoesRuin()){
					myWorld.OverlaySchematicForever(myWorld.Entities.get(EntIndex).Render());
				}
				myWorld.Entities.remove(EntIndex);
				EntIndex--;
			}
			// NO CODE CAN GO HERE
		}
		myGame.repaint();
	}
}

public class SpaceTrek extends Applet implements KeyListener {
	BufferedImage bf;
	private static final long serialVersionUID = 1L;
	int width, height;
	int x, y;
	World myWorld;
	
	public void init() {
		myWorld = new World(40,40);
		width = getSize().width;
		height = getSize().height;
		setBackground( Color.black );
		x = 20;
		y = 20;
		addKeyListener( this );
		Timer UpdateTimer = new Timer();
		RemindTask Updater = new RemindTask();
		Updater.myWorld = myWorld;
		Updater.myGame = this;
		UpdateTimer.schedule(Updater, 1000, 1000);
	}

	public void keyPressed(KeyEvent e) {       
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			if (myWorld.canIMoveThere(x-1, y)){
				x--;
			}
		}
		else if (key == KeyEvent.VK_RIGHT) {
			if (myWorld.canIMoveThere(x+1, y)){
				x++;
			}
		}
		else if (key == KeyEvent.VK_UP) {
			if (myWorld.canIMoveThere(x, y-1)){
				y--;
			}
		}
		else if (key == KeyEvent.VK_DOWN) {
			if (myWorld.canIMoveThere(x, y+1)){
				y++;
			}
		}
		repaint();
	}

	public void keyReleased( KeyEvent e ) { }
	public void keyTyped( KeyEvent e ) { 
		if (e.getKeyChar() == 'a'){
			if (myWorld.canIMoveThere(x-1, y)){
				x--;
			}
		}
		if (e.getKeyChar() == 'd'){
			if (myWorld.canIMoveThere(x+1, y)){
				x++;
			}
		}
		if (e.getKeyChar() == 'w'){
			if (myWorld.canIMoveThere(x, y-1)){
				y--;
			}
		}
		if (e.getKeyChar() == 's'){
			if (myWorld.canIMoveThere(x, y+1)){
				y++;
			}
		}
		if (e.getKeyChar() == 'x'){
			myWorld.Entities.add(new LittleRabbit(x,y,myWorld));
		}
		if (e.getKeyChar() == 'q'){
			myWorld.Entities.add(new Wolf(x,y,myWorld));
		}
		if (e.getKeyChar() == 'b'){
			myWorld.Entities.add(new Bomb(x,y,myWorld));
		}
		if (e.getKeyChar() == 'e'){
			int localx = 0;
			int localy = 0;
			for (localx = 0;localx<9;localx++){
				for (localy = 0;localy<9;localy++){
					if (myWorld.isOnWorld(x+localx-5,y+localy-5)){
						if (myWorld.getPoint(x+localx-5, y+localy-5).PermCharacter == '.'){
							myWorld.setPointForever(x+localx-5,y+localy-5,';',false,Color.green);
						}
					}
				}
			}
		}
		repaint();
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint( Graphics endg ) {
		width = getSize().width;
		height = getSize().height;
		bf = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bf.getGraphics();
		myWorld.ClearField();
		for (int EntIndex=0; EntIndex < myWorld.Entities.size(); EntIndex++){
			myWorld.OverlaySchematic(myWorld.Entities.get(EntIndex).Render());
		}
		myWorld.setPoint(x, y, '@', false, Color.white);
		Schematic OutputData = myWorld.Render(x-10, y-10, 20, 20);
		
		int fontsize = (int) (20 * ((float)((width+height)/2)/500));
		g.setFont(new Font("Courier New", Font.PLAIN, fontsize));
		
		int xspace = (width-10)/20;
		int yspace = (height-10)/20;
		
		for (int x = 0; x < OutputData.LengthX; x++){
			for (int y = 0; y < OutputData.LengthY; y++){
				g.setColor(OutputData.SchemoTiles[x][y].fgColor);
				g.drawString(Character.toString(OutputData.SchemoTiles[x][y].Character), 10+(xspace*x), 10+(yspace*y));
			}
		}
		endg.drawImage(bf, 0, 0, null);
	}
}