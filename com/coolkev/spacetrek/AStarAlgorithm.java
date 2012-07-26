package com.coolkev.spacetrek;

/*import java.util.ArrayList;

class Point{
	
	Point Parent;
	int x;
	int y;
	int F;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	//TODO override equals
}

class AStarAlgorithm {
	
	ArrayList<Point> openlist = new ArrayList<Point>(1);
	ArrayList<Point> closedlist = new ArrayList<Point>(1);
	
	World ParentWorld;
	
	Point StartPoint;
	Point EndPoint;
	
	boolean Normal = true;
/*	
1) Add the starting square (or node) to the open list.

2) Repeat the following:

	a) Look for the lowest F cost square on the open list. We refer to this as the current square.
	
	b) Switch it to the closed list.
	
	c) For each of the 8 squares adjacent to this current square 
	
	    1) If it is not walkable or if it is on the closed list, ignore it. Otherwise do the following.           
	
	    2) If it isn’t on the open list, add it to the open list. Make the current square the parent of this square. Record the F, G, and H costs of the square. 
	
	    3) If it is on the open list already, check to see if this path to that square is better, using G cost as the measure. A lower G cost means that this is a better path. If so, change the parent of the square to the current square, and recalculate the G and F scores of the square. If you are keeping your open list sorted by F score, you may need to resort the list to account for the change.
	
	d) Stop when you:
	
	    1) Add the target square to the closed list, in which case the path has been found (see note below), or
	    Fail to find the target square, and the open list is empty. In this case, there is no path.   

3) Save the path. Working backwards from the target square, go from each square to its parent square until you reach the starting square. That is your path. 
/
	public Point Compute(){
		if (Normal){/*TODO Implement the normal method/}else{
			
			// Dijkstra
			openlist.add(StartPoint);
			openlist.get(0).F = 1;
			while (true){
				Point CurrentPoint = new Point(0,0);
				CurrentPoint.F = 10000;
				for (int index = 0; index < openlist.size(); index++){
					if (openlist.get(index).F < CurrentPoint.F){
						CurrentPoint = openlist.get(index);
					}
				}
				openlist.remove(CurrentPoint);
				closedlist.add(CurrentPoint);
				// FOR EACH OF THE POINTS NEXT TO CURRENTPOINT
				if (ParentWorld.canIMoveThere(CurrentPoint.x, CurrentPoint.y) == false && closedlist.contains(o))
				
			}
			
		}
	}
	
}
*/