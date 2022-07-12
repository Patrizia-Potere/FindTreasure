//You need to find as many treasure as you can in a map of 10X10.
//Your know your initial position which will be assign randomly.
//There are 2 small treasures and a big one.
//There are three manholes.
//You can choose between two characters; Both have 3 lives but different weapons.
//One character has a rope which allows him to escape from the manhole without loosing a life.
//The other character has a magic ball which allows him to get one of the coordinates of one of the small treasures,
//but you don't know if is x or y, and he will loose a life.
//You can use your weapon only once.
//You go on till you will find the exit.
//Final score can be max 1500 (small treasure 100, small treasure 350, big treasure 1000). 


import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Map position = new Map();		//Create object of Map class
		position.CheckValues();
		Character person = new Character("Indiana",3,"rope");	//Create object of Character class
		Character friar = new Character("Soothsayer Friar",3,"magicBall");		
		System.out.println("Find the treasure!");
		System.out.println("You can choose between:");
		System.out.println("1. "+person.getName()+", "+person.getLife()+" lives, "+"weapon : "+person.getWeapon());
		System.out.println("2. "+friar.getName()+", "+friar.getLife()+" lives, "+"weapon : "+friar.getWeapon());
		Scanner choice = new Scanner(System.in); //let user choose the character
		int number = choice.nextInt();			// read choice
		int avatar=0;
		String tool="";
		switch (number) {						//assignment based on user choice
			case 1:
				avatar = person.getLife();
				tool = person.getWeapon();
				break;
			case 2:
				avatar = friar.getLife();
				tool = friar.getWeapon();
				break;
		}
		System.out.println("Current position: "+position.getCurrentX()+","+position.getCurrentY());
		//For testing purpose
//		System.out.println("Exit: "+position.getExitX()+","+position.getExitY());
//		System.out.println("Trap1: "+position.getManHoleX()+","+position.getManHoleY());
//		System.out.println("Trap2: "+position.getManHoleX1()+","+position.getManHoleY1());
//		System.out.println("Trap3: "+position.getManHoleX2()+","+position.getManHoleY2());
//		System.out.println("Treasure1: "+position.getTreasureX()+","+position.getTreasureY());
//		System.out.println("Treasure2: "+position.getTreasureX1()+","+position.getTreasureY1());
//		System.out.println("Diamond: "+position.getDiamondX()+","+position.getDiamondY());
		
		//Till user finds exit and still has lives
		while ((avatar-position.getDead())>0 && (position.getCurrentX()!= position.getExitX() || position.getCurrentY()!= position.getExitY())) {			
				System.out.println("Choose direction (N,S,E,W) : ");
				Scanner sc  = new Scanner(System.in);	//Create Scanner Object
				String move = sc.nextLine();			//Read user input
				position.Move(move,avatar,tool);			//Calling Move Method from Map class
			}
		//if exit has been found
		if (position.getCurrentX()== position.getExitX() && position.getCurrentY()== position.getExitY()) {
			System.out.println("Congratulation, you have found the exit!");
			System.out.println("Your final score is "+position.getScore());
		}	
	}
}
