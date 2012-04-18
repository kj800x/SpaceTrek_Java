import java.awt.Color;

// TODO Write JAVADOCS for 'Bomb'
public class Bomb extends Entity {
	
	public Bomb(int X, int Y, World TheWorld){
		startX = X;
		startY = Y;
		myWorld = TheWorld;
		super.Ruins = true; //Two Ruins being created. The second one is being used as this whereas the first one is later checked
	}
	
	Schematic Render() {
        Schematic Output;
        switch (Stage) {
        case 0: Output = new Schematic(1,1);
        		topleftX = startX;
        		topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
        		Output.SchemoTiles[0][0].Character = '^';
        		Output.SchemoTiles[0][0].fgColor = Color.red;
        		Output.SchemoTiles[0][0].Collision = true;
                break;
        case 1: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '>';
        		Output.SchemoTiles[0][0].fgColor = Color.red;
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 2: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = 'v';
        		Output.SchemoTiles[0][0].fgColor = Color.red;
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 3: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '<';
        		Output.SchemoTiles[0][0].fgColor = Color.red;
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 4: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '^';
        		Output.SchemoTiles[0][0].fgColor = Color.red;
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 5: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '*';
        		Output.SchemoTiles[0][0].fgColor = new Color(255,0,0);
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 6: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '*';
        		Output.SchemoTiles[0][0].fgColor = new Color(255,200,0);
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 7: Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][0].Character = '*';
        		Output.SchemoTiles[0][0].fgColor = new Color(255,100,0);
				Output.SchemoTiles[0][0].Collision = true;
                break;
        case 8: Output = new Schematic(3,3);
				topleftX = startX-1;
				topleftY = startY-1;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
				Output.SchemoTiles[0][1].Character = '*';
        		Output.SchemoTiles[0][1].fgColor = new Color(212,212,212);
				Output.SchemoTiles[0][1].Collision = true;
				
				Output.SchemoTiles[1][1].Character = '*';//DBBDA9
        		Output.SchemoTiles[1][1].fgColor = new Color(219,189,169);
				Output.SchemoTiles[1][1].Collision = true;
				
				Output.SchemoTiles[1][0].Character = '*';
        		Output.SchemoTiles[1][0].fgColor = new Color(212,212,212);
				Output.SchemoTiles[1][0].Collision = true;

				Output.SchemoTiles[2][1].Character = '*';
        		Output.SchemoTiles[2][1].fgColor = new Color(212,212,212);
				Output.SchemoTiles[2][1].Collision = true;
				
				Output.SchemoTiles[1][2].Character = '*';
        		Output.SchemoTiles[1][2].fgColor = new Color(212,212,212);
				Output.SchemoTiles[1][2].Collision = true;
                break;
        default:Output = new Schematic(1,1);
				topleftX = startX;
				topleftY = startY;
        		Output.TopLeftX = topleftX;
        		Output.TopLeftY = topleftY;
        		Output.SchemoTiles[0][0].Character = '?';
        		Output.SchemoTiles[0][0].Collision = false;
                break;
        }
		return Output;
	}

	void CheckForStageChange() {
        switch (Age) {
        case 1: Stage = 1;
                break;
        case 2: Stage = 2;
        		break;
        case 3: Stage = 3;
        		break;
        case 4: Stage = 4;
                break;
        case 5: Stage = 5;
                break;
        case 6: Stage = 6;
        		break;
        case 7: Stage = 7;
        		break;
        case 8: Stage = 8;
        		break;
        case 9: Ruins = true;
        		Dead = true;
        		break;
        default:break;
        }
	}
}