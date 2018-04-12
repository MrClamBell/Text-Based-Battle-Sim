package inventory;
public class Weapon {
	public String name;
	public int damage;
	public int defense;
	public Weapon(int damage, int defense, String name){
		this.damage = damage;
		this.name = name;
		this.defense = defense;
	}	
}