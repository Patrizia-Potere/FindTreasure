import java.util.HashMap;

public class Map {
	private int X = 10;		//X and Y size of Map
	private int Y = 10;
	private int dead = 0,score=0;		//dead for counting lives
	private int coordinate;				//to reveal coordinate for magic ball
	boolean bRope=false,bMagic=false;	//allow to use weaponS just one
	boolean btres=false,btres1=false,bdiamond=false;	//allow to find treasures once;
	private int currentX,currentY;		//current position of user
	private int manHoleX,manHoleY,manHoleX1,manHoleY1,manHoleX2,manHoleY2; //traps coordinates
	private int diamondX,diamondY;		//big treasure coordinate
	private int treasureX,treasureY,treasureX1,treasureY1;		//treasures coordinates
	private int exitX = (int) Math.floor(Math.random()*10+1);	//Coordinates for Exit (1-10)
	private int exitY = (int) Math.floor(Math.random()*10+1);
	private int [][] mapArray = new int[10][10]; //two dimensions array to mark coordinate already assigned
	
//	Getter to allow access to private variable
	public int getCurrentX() {
		return currentX;
	}
	public int getCurrentY() {
		return currentY;
	}
	public int getScore() {
		return score;
	}
	public int getDead() {
		return dead;
	}
	public int getExitX() {
		return exitX;
	}
	public int getExitY() {
		return exitY;
	}
// for testing purpose
//	public int getManHoleX() {
//		return manHoleX;
//	}
//	public int getManHoleY() {
//		return manHoleY;
//	}
//	public int getManHoleX1() {
//		return manHoleX1;
//	}
//	public int getManHoleY1() {
//		return manHoleY1;
//	}
//	public int getManHoleX2() {
//		return manHoleX2;
//	}
//	public int getManHoleY2() {
//		return manHoleY2;
//	}
//	public int getTreasureX() {
//		return treasureX;
//	}
//	public int getTreasureY() {
//		return treasureY;
//	}
//	public int getTreasureX1() {
//		return treasureX1;
//	}
//	public int getTreasureY1() {
//		return treasureY1;
//	}
//	public int getDiamondX() {
//		return diamondX;
//	}
//	public int getDiamondY() {
//		return diamondY;
//	}
	
//-----------------------------------------------------------------
	
// Assign coordinate and checking they are all different
public void CheckValues() {
	for (int i=0; i<X; i++) {
		for (int j=0; j<Y; j++) {
			mapArray[i][j]=0;		//every coordinate is available
		}
	}
	mapArray[exitX-1][exitY-1]=1;
	do {
		currentX = (int) Math.floor(Math.random()*10+1);
		currentY = (int) Math.floor(Math.random()*10+1);
			mapArray[currentX-1][currentY-1]++;
	}
	while (mapArray[currentX-1][currentY-1]>1);
	do {
		manHoleX = (int) Math.floor(Math.random()*10+1);
		manHoleY = (int) Math.floor(Math.random()*10+1);
			mapArray[manHoleX-1][manHoleY-1]++;
	}
	while (mapArray[manHoleX-1][manHoleY-1]>1);
	do {
		manHoleX1 = (int) Math.floor(Math.random()*10+1);
		manHoleY1 = (int) Math.floor(Math.random()*10+1);
			mapArray[manHoleX1-1][manHoleY1-1]++;
	}
	while (mapArray[manHoleX1-1][manHoleY1-1]>1);
	do {
		manHoleX2 = (int) Math.floor(Math.random()*10+1);
		manHoleY2 = (int) Math.floor(Math.random()*10+1);
			mapArray[manHoleX2-1][manHoleY2-1]++;
	}
	while (mapArray[manHoleX2-1][manHoleY2-1]>1);
	do {
		treasureX = (int) Math.floor(Math.random()*10+1);
		treasureY = (int) Math.floor(Math.random()*10+1);
			mapArray[treasureX-1][treasureY-1]++;
	}
	while (mapArray[treasureX-1][treasureY-1]>1);
	do {
		treasureX1 = (int) Math.floor(Math.random()*10+1);
		treasureY1 = (int) Math.floor(Math.random()*10+1);
			mapArray[treasureX1-1][treasureY1-1]++;
	}
	while (mapArray[treasureX1-1][treasureY1-1]>1);
	do {
		diamondX = (int) Math.floor(Math.random()*10+1);
		diamondY = (int) Math.floor(Math.random()*10+1);
			mapArray[diamondX-1][diamondY-1]++;
	}
	while (mapArray[diamondX-1][diamondY-1]>1);
}
//--------------------------------------------------------------------------	

// Method to determine position of user within the map
	
	public void Move(String direction, int life, String weapon) {
		//HashMap for big treasure
		HashMap<String, Integer> prize = new HashMap<String, Integer>();
		prize.put("Diamond",500);
		prize.put("Emerald",300);
		prize.put("Ruby",200);
		
		// If direction is North
		if (direction.equals("N")) {
			if (currentX == X) {		//top edge of Map
				currentX=0;
			}else currentX++;
		}
		// If direction is South
		if (direction.equals("S")) {
			if (currentX == 0) {		//bottom edge of Map
				currentX = X;
			}else currentX--;
		}
		// If direction is East
		if (direction.equals("E")) {	//right edge of Map
			if (currentY == Y) {
				currentY = 0;
			}else currentY++;
		}
		// If direction is West
		if (direction.equals("W")) {	//left edge of Map
			if (currentY == 0) {
				currentY = Y;
			}else currentY--;
		}
		//Checking user input for direction in not empty and correct letter
		if (direction.equals(" ") || !direction.equals("N") && !direction.equals("S") && !direction.equals("E") && !direction.equals("W")) {
			System.out.println("Please enter a correct direction.");
		}
		//If fell into one of the manholes
		if ((currentX==manHoleX && currentY==manHoleY)|| (currentX==manHoleX1 && currentY==manHoleY1) || (currentX==manHoleX2 && currentY==manHoleY2)) {
			System.out.println("Oh no! You fell into a manhole");
			dead++;
			//if weapon is the rope escape without loosing a life
			if ((weapon.equals("rope"))&& (bRope==false)) {
				dead--;
				bRope=true;
				System.out.println("You can escape using your rope");
			}
			//if weapon is magic ball you get a treasure position
			if (weapon.equals("magicBall") && (bMagic==false)) {
				if (btres==true&&btres1==true&& (life-dead)>0) {
					System.out.println("You have already found both treasures.");
					System.out.println("What a shame! Your weapon is useless");
				} else {
					bMagic=true;
					coordinate = (int) Math.floor(Math.random()*4);
					switch (coordinate) {
						case 0:
							System.out.println("Treasure is in position:" +treasureX);
							break;
						case 1:
							System.out.println("Treasure is in position:" +treasureY);
							break;
						case 2:
							System.out.println("Treasure is in position:" +treasureX1);
							break;
						case 3:
							System.out.println("Treasure is in position:" +treasureY1);
							break;
					}
				}
			}
			// if no more lives left
			if ((life-dead)==0) {
				System.out.println("Game Over!");			
			}else {
				System.out.println("Try again!You still have "+(life-dead)+" life");
			}
			//if user finds a treasure
		} else if (currentX==treasureX && currentY==treasureY && btres==false) {
			btres = true;
			System.out.println("Congratulation! You found a treasure.");
			score +=150;
			System.out.println("You earned 100 points");
		} else if (currentX==treasureX1 && currentY==treasureY1 && btres1==false){
			btres1 = true;
			System.out.println("Congratulation! You found a treasure.");
			score +=350;
			System.out.println("You earned 350 points");
		//if user finds big treasure
		} else if (currentX==diamondX &&currentY==diamondY && bdiamond==false) {
			bdiamond = true;
			System.out.println("Yeahh, you found the BIG Treasure");
				for (String i : prize.keySet()) {		//loop through 
					System.out.println(i+ " worth " +prize.get(i));
				}
			score +=1000;
			System.out.println("You earned 1000 points");
		//Display position based on user input	
		}else System.out.println("Your position is : "+currentX+","+currentY); 
	}
}


