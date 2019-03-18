package entities;
import java.util.ArrayList;
import java.util.List;

public class Rover {
	int x;
	int y;
	String facing;
	Plateau plateau;

	List<String> directions = new ArrayList<String>() {{
		add("N");
		add("E");
		add("S");
		add("W");
	}};

	public Rover(Plateau plateau, int x, int y, String facing) {
		this.plateau = plateau;
		this.x = x;
		this.y = y;
		this.facing = facing;
	}
	
	public String location() {
		return String.format("%d %d %s", this.x, this.y, this.facing);
	}
	
	public void move(char move) {
		int index = this.directions.indexOf(this.facing);
		if (move == 'L') {
			if (index == 0) {
				index = this.directions.size() - 1;
			} else {
				index -= 1;
			}
			this.facing = this.directions.get(index);
		}
		if (move == 'R') {
			if (index == this.directions.size() - 1) {
				index = 0;
			} else {
				index += 1;
			}
			this.facing = this.directions.get(index);
		}
		if (move == 'M') {
			switch (this.facing) {
			case "N":
				this.y += 1;
				if (this.y >= this.plateau.y) {
					this.y = this.plateau.y;
				}
				break;
			case "S":
				this.y -= 1;
				if (this.y <= 0) {
					y = 0;
				}
				break;
			case "E":
				this.x += 1;
				if (this.x >= this.plateau.x) {
					this.x = this.plateau.x;
				}
				break;
			case "W":
				this.x -= 1;
				if (this.x <= 0) {
					this.x = 0;
				}
				break;
			}
		}
	}
}
