import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;

/** This class contains the methods and fields of the WORLD OBJECT for SpaceTrek
 * @author Kevin Johnson
 * @param XLength (int) How many characters the world is horizontally.
 * @param YLength (int) How many characters the world is vertically.
 * @param DefaultCharacter (char) What layer 1 and 2 of the world is filled with by default.
 * **/
class World {

	private static final Color GrassGreenOne = new Color(145,237,146);
	private static final Color GrassGreenTwo = new Color(97, 232,100);
	
	ArrayList<Entity> Entities = new ArrayList<Entity>(1);
	
	/** The world is stored in this object.
	 * Dimensions (1 and 2) are (X and Y) respectively, and self explanatory.
	 * **/
	private Tile[][] WorldTiles;
	
	/** The only constructor for this class.
     * @param XLength (int) How many characters the world is horizontally.
     * @param YLength (int) How many characters the world is vertically.
     * @param DefaultCharacter (char) What layer 1 and 2 of the world is filled with by default.
	 */
	public World(int XLength, int YLength){
		super();
		ResetField(XLength, YLength);
	}
	/** This method resets the field to defaults.
	 * @param XLength (int) the new horizontal length of the field.
	 * @param YLength (int) the new vertical length of the field.**/
	
	public void ResetField(int XLength, int YLength) {
		WorldTiles = new Tile[XLength][YLength];
		for (int x=0; x<XLength; x++){
			for (int y=0; y<YLength; y++){
				if (Math.random()*10 > (double)2 ){
					WorldTiles[x][y] = new Tile('.', GrassGreenOne);
				}
				else{
					WorldTiles[x][y] = new Tile(',', GrassGreenTwo);
				}
				WorldTiles[x][y].Collision = false;
			}
		}
	}
	/** ClearField will reset the field before each frame. **/
	public void ClearField() {
		for (int x=0; x<getXLength(); x++){
			for (int y=0; y<getYLength(); y++){
				WorldTiles[x][y].Collision=WorldTiles[x][y].PermCollision;
				WorldTiles[x][y].Character=WorldTiles[x][y].PermCharacter;
				WorldTiles[x][y].fgColor  =WorldTiles[x][y].PermfgColor;
			}
		}
	}
	/** Method will overlay a Schematic onto the World.
	 * @param toOverlay (Schematic) The Schematic we will overlay.
	 */
	
	public void OverlaySchematic(Schematic toOverlay) {
		for (int x = 0; x < toOverlay.LengthX; x++){
			for (int y = 0; y < toOverlay.LengthY; y++){
				if (toOverlay.SchemoTiles[x][y].Character != '\0'){
					setPoint(toOverlay.TopLeftX+x, toOverlay.TopLeftY+y, toOverlay.SchemoTiles[x][y].Character, toOverlay.SchemoTiles[x][y].Collision, toOverlay.SchemoTiles[x][y].fgColor);
				}
			}
		}
	}

	public void OverlaySchematicForever(Schematic toOverlay) {
		for (int x = 0; x < toOverlay.LengthX; x++){
			for (int y = 0; y < toOverlay.LengthY; y++){
				if (toOverlay.SchemoTiles[x][y].Character != '\0'){
					setPointForever(toOverlay.TopLeftX+x, toOverlay.TopLeftY+y, toOverlay.SchemoTiles[x][y].Character, toOverlay.SchemoTiles[x][y].Collision, toOverlay.SchemoTiles[x][y].fgColor);
				}
			}
		}
	}
	/** Simple getter for if you can move to point x,y in layer 3. 
	 * @param x (int) the x location of where you are checking.
	 * @param y (int) the y location of where you are checking.
	 * @return (boolean) if you can move there**/

	public boolean canIMoveThere(int x, int y) {
		if (x>0 && y>0 && x<getXLength() &&  y<getYLength()){
			if (WorldTiles[x][y].Collision){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	/** Simple setter for a single point that sets layer 1 
	 * @param x (int) the x location of where you are changing.
	 * @param y (int) the y location of where you are changing.
	 * @param toWhat (char) what you are changing it to.
	 * @param collides (boolean) Whether there is a collision there.**/

	public void setPoint(int x, int y, char toWhat, boolean collides, Color fgcolor) {
		WorldTiles[x][y].Character = toWhat;
		WorldTiles[x][y].Collision = collides;
		WorldTiles[x][y].fgColor   = fgcolor;
	}
	public void setPointForever(int x, int y, char toWhat, boolean collides, Color fgcolor) {
		WorldTiles[x][y].PermCharacter = toWhat;
		WorldTiles[x][y].PermCollision = collides;
		WorldTiles[x][y].PermfgColor   = fgcolor;
	}

	/**
	 * @param topleftX (int) The X location of the top left corner of the rectangle.
	 * @param topleftY (int) The Y location of the top left corner of the rectangle.
	 * @param lengthX  (int) How many points on the X direction will it render out.
	 * @param lengthY  (int) How many points on the Y direction will it render out. 
	 * @return (String) That corresponds to the rectangle called for. (Out of the board is a space) 
	 * **/
	public Schematic Render(int topleftX, int topleftY, int lengthX, int lengthY){
		Schematic Output = new Schematic(lengthX, lengthY);
		for (int x = topleftX; x < topleftX+lengthX; x++){
			for (int y = topleftY; y < topleftY+lengthY; y++){
				if (x>0 && x<getXLength() && y>0 && y<getYLength()){
					Output.SchemoTiles[x-topleftX][y-topleftY].Character = WorldTiles[x][y].Character;
					Output.SchemoTiles[x-topleftX][y-topleftY].fgColor = WorldTiles[x][y].fgColor;
					Output.SchemoTiles[x-topleftX][y-topleftY].Collision = WorldTiles[x][y].Collision;
				}
				else{
					Output.SchemoTiles[x-topleftX][y-topleftY].Character = ' ';
					Output.SchemoTiles[x-topleftX][y-topleftY].fgColor   = Color.white;
					Output.SchemoTiles[x-topleftX][y-topleftY].Collision = true;
				}
			}
		}
		return Output;
	}
	
	/**Getter for the length of the world in the X direction
	 * @return (int) X-Length**/
	public int getXLength() {
		return WorldTiles.length;
	}

	/**Getter for the length of the world in the Y direction
	 * @return (int) Y-Length**/
	public int getYLength() {
		return WorldTiles[0].length;
	}
}