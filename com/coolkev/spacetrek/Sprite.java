package com.coolkev.spacetrek;

import java.io.*;
import java.util.ArrayList;

class Sprite {

	ArrayList<Schematic> Schems = new ArrayList<Schematic>(16);

	public Sprite(){
		Schematic a = new Schematic(2, 2);
		a.SchemoTiles[0][0].Character='a';
		a.SchemoTiles[1][0].Character='b';
		Schems.add(a);
		Schematic b = new Schematic(2, 2);
		b.SchemoTiles[0][0].Character='x';
		b.SchemoTiles[1][0].Collision=true;
		b.SchemoTiles[1][0].Character='z';
		Schems.add(b);
	}

	public void Save(String FileName){
		try {
			// Catch errors in I/O if necessary.
			// Open a file to write to, named SavedObjects.sav.
			FileOutputStream saveFile=new FileOutputStream(FileName);

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Now we do the save.
			save.writeObject(Schems);

			// Close the file.
			save.close(); // This also closes saveFile.
		}
		catch(Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

	@SuppressWarnings("unchecked")
	public void Load(String FileName){	  
		// Wrap all in a try/catch block to trap I/O errors.
		try {
			// Open file to read from, named SavedObjects.sav.
			FileInputStream saveFile = new FileInputStream(FileName);

			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);

			// Now we do the restore.
			// readObject() returns a generic Object, we cast those back
			// into their original class type.
			// For primitive types, use the corresponding reference class.
			Schems = (ArrayList<Schematic>) save.readObject();

			// Close the file.
			save.close(); // This also closes saveFile.
		}
		catch(Exception exc){
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}
}