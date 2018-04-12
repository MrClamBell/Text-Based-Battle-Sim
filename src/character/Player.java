package character;
import inventory.*;
public class Player {
	public Weapon weapon;
	public int health;
	public Player(int health, Weapon weapon){
		this.health = health;
		this.weapon = weapon;
	}
}