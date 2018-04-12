package inventory;
public class RangedWeapon {
	public int damage;
	public int range;
	public int defense;
	public String name;
	public RangedWeapon(int damage, int defense, int range, String name){
		this.damage = damage;
		this.range = range;
		this.name = name;
		this.defense = defense;

	}
}