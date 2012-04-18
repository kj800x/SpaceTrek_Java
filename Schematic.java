/** This class details how to pass parts of the world between functions. '\0' is used for transparency. 
 * @author Kevin Johnson**/
class Schematic {
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
}