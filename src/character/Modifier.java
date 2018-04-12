package character;

import java.util.ArrayList;

public class Modifier {
	public int stat;
	public Modifier(int stat){
		this.stat = stat;
		ArrayList<Integer> modifier = new ArrayList<Integer>();
		for(int x = -3; x < 4; x++){
			modifier.add(x);
		}
	}
}