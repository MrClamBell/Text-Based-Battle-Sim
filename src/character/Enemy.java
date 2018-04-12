package character;
import inventory.*;
public class Enemy {
	public int health;
	public Weapon weapon;
	public Enemy(int health, Weapon weapon){
		this.health = health;
		this.weapon = weapon;
	}
}