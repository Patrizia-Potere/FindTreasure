public class Character {
	private String name;
	private int life;
	private String weapon;
	
//Define Constructor for character with 3 parameters	
	public Character(String name, int life, String weapon) {
		this.name = name;
		this.life = life;
		this.weapon = weapon;
	}
// Getter to allow access to private variable (No Setter)	
	public String getName() {
		return name;
	}
	public int getLife() {
		return life;
	}
	public String getWeapon() {
		return weapon;
	}
}
