package main;
import java.util.*;
import java.applet.*;
import inventory.*;
import character.*;
@SuppressWarnings("serial")
public class MainM extends Applet{
	public static ArrayList<String> options = new ArrayList<String>();
	public int SwordDamage = 20;
	public int SwordDefense = 10;
	public int fistDamage = 10;
	public int fistDefense = 2;
	public int bowDamage = 30;
	public int bowDefense = 5;
	public int bowRange = 40;
	public int hammerDamage = 40;
	public int hammerDefense = 5;
	public Weapon sword = new Weapon(SwordDamage, SwordDefense, "Sword");
	public Weapon fist = new Weapon(fistDamage, fistDefense, "Fist");
	public RangedWeapon bow = new RangedWeapon(bowDamage, bowDefense, bowRange, "Bow");
	public Weapon hammer = new Weapon(hammerDamage, hammerDefense, "Hammer");
	public int invsize = 3;
	public ArrayList<Weapon> inventory = new ArrayList<Weapon>();
	public Random rnd = new Random();
  public Player player = new Player(rnd.nextInt(201-150)+150, null);
    public Enemy enemy = new Enemy(rnd.nextInt(201-150)+150, null);
	public Scanner scan = new Scanner(System.in);
	public Weapon RandomWeapon(){
		int index = rnd.nextInt(inventory.size());
		Weapon rweapon = inventory.get(index);
		//System.out.println(rweapon.name);
		return rweapon;
	}
	public void Attack(){
		while(true){
			String yn;
			sword.damage = SwordDamage;
			hammer.damage = hammerDamage;
			fist.damage = fistDamage;
			bow.damage = bowDamage;
			enemy.weapon = RandomWeapon();
			System.out.println("Enemy has " + enemy.weapon.name);
			System.out.println("attack with what:");
			PrintInventory(inventory);
			yn = scan.nextLine();
			SelectWeapon(player, yn);
			//System.out.println(player.weapon.name);
			if (player.health>0&&enemy.health<=0){
				System.out.println("You Win");
				System.exit(0);
			}
			else if (player.health<=0&&enemy.health>0){
				System.out.println("You lose");
				System.exit(0);
			}
			else if(enemy.health>0&&player.health>0){
				System.out.println("you attack with " + player.weapon.name);
				if(rnd.nextInt(10) == rnd.nextInt(10)){
					player.weapon.damage = player.weapon.damage * 3;
					System.out.println("Critical Hit!");
				}
				enemy.health = (enemy.health - (player.weapon.damage - (enemy.weapon.defense/2)));
				System.out.println("Enemy took "+ (player.weapon.damage - (enemy.weapon.defense /2)) + " damage" + "\nenemy now has " + enemy.health + " health");
				if (player.health>0&&enemy.health<=0){
					System.out.println("You Win");
					System.exit(0);
				}
				System.out.println("enemy attacks with " + enemy.weapon.name);
				player.health = (player.health - (enemy.weapon.damage - (player.weapon.defense /2)));
				System.out.println("you took " + (enemy.weapon.damage - (player.weapon.defense /2))+ " damage" + "\nyou now have "+ player.health + " health");
				if (player.health<=0&&enemy.health>0){
					System.out.println("You lose");
					System.exit(0);
				}
				break;
			}
		}
	}
	public void Defend(){
		while(true){
			String yn;
			enemy.weapon = RandomWeapon();
			if(enemy.health>0&&player.health>0){
				System.out.println("defend with what:");
				PrintInventory(inventory);
				yn = scan.nextLine();
				SelectWeapon(player, yn);
				System.out.println("enemy attacks with " + enemy.weapon.name);
				System.out.println("You take " + (enemy.weapon.damage - player.weapon.defense) + " damage");
				player.health = (player.health - (enemy.weapon.damage - player.weapon.defense));
				if(rnd.nextInt(10) == rnd.nextInt(10)){
					System.out.println("You Parry");
					System.out.println("Enemy takes " + player.weapon.damage + " damage");
					enemy.health = (enemy.health - player.weapon.damage);
					System.out.println("enemy now has " + enemy.health + " health");
				}
				System.out.println("you now have "+player.health+" health");
				break;
			}
			else if (player.health>0&&enemy.health<=0){
				System.out.println("You Win");
				System.exit(0);
			}
			else if (player.health<=0&&enemy.health>0){
				System.out.println("You lose");
				System.exit(0);
			}
		}
	}
	public void Run(){
		while(true){
			enemy.weapon=RandomWeapon();
			if(enemy.health>0&&player.health>0){
				if(rnd.nextInt(10)>=rnd.nextInt(20)){
					System.out.println("You got away safely");
					System.exit(0);
				}
				else{
					System.out.println("you didn't get away");
					DoNothing();
					break;
				}
			}
			else if (player.health>0&&enemy.health<=0){
				System.out.println("You Win");
				System.exit(0);
			}
			else if (player.health<=0&&enemy.health>0){
				System.out.println("You lose");
				System.exit(0);
			}
		}
	}
	public void DoNothing(){
		while(true){
			if(enemy.health>0&&player.health>0){
				enemy.weapon = RandomWeapon();
				System.out.println("You do nothing");
				System.out.println("enemy attacks with " + enemy.weapon.name);
				player.health = player.health - enemy.weapon.damage;
				System.out.println("you took " + (enemy.weapon.damage)+ " damage" + "\nyou now have "+ player.health + " health");
				break;
			}
			else if (player.health>0&&enemy.health<=0){
				System.out.println("You Win");
				System.exit(0);
			}
			else if (player.health<=0&&enemy.health>0){
				System.out.println("You lose");
				System.exit(0);
			}
		}
	}
	public void Exit() {
		System.exit(0);
	}
	public static void main(String[] args){
		MainM m = new MainM();
		m.MakeInventory(m.inventory, m.sword);
		m.MakeInventory(m.inventory, m.hammer);
		m.MakeInventory(m.inventory, m.fist);
		Scanner scan = m.scan;
		String yn;
		options.add("Attack");
		options.add("Defend");
		options.add("Run");
		options.add("Do Nothing");
		options.add("Exit");
		while(true){
			System.out.println("What would you like to do?");
			m.List(options);
			yn = scan.nextLine();
			if(yn.equalsIgnoreCase("Attack")||yn.equalsIgnoreCase("1")){
				m.Attack();
			}
			else if(yn.equalsIgnoreCase("Defend")||yn.equalsIgnoreCase("2")){
				m.Defend();
			}
			else if(yn.equalsIgnoreCase("Run")||yn.equalsIgnoreCase("3")){
				m.Run();
			}
			else if(yn.equalsIgnoreCase("Do Nothing")||yn.equalsIgnoreCase("4")){
				m.DoNothing();
			}
			else if(yn.equalsIgnoreCase("Exit")||yn.equalsIgnoreCase("5")){
				m.Exit();
			}

			else {
				System.out.println("Command Not Recognized");
			}
		}
	}
	public void MakeInventory(ArrayList<Weapon> inventory, Weapon weapon){
		inventory.add(weapon);
	}
	public void PrintInventory(ArrayList<Weapon> inventory){
		//System.out.println("Inventory:");
		int i = 0;
		for(Weapon s : inventory){
			i++;
			System.out.println(i + ". " + s.name);
		}
	}
	public void List(ArrayList<String> foo) {
		for(int i = 0; i < foo.size(); i++)
			System.out.println((i+1) + ". " + foo.get(i));
	}
	public void SelectWeapon(Player player, String yn){

		if(yn.equalsIgnoreCase("sword")){
			player.weapon = sword;
		}
		else if(yn.equalsIgnoreCase("fist")){
			player.weapon = fist;
		}
		else if(yn.equalsIgnoreCase("hammer")){
			player.weapon = hammer;
		}
		else {
			player.weapon = inventory.get(Integer.parseInt(yn)-1);
		}
	}
}
