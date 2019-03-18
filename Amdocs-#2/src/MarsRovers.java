import java.util.ArrayList;
import java.util.List;

import entities.Plateau;
import entities.Rover;

public class MarsRovers {
	
	static Plateau plateau;
	static List<Rover> rovers;
	
	public static void main(String[] args) {
		
		String[] commands = {
			"5 5",
			"1 2 N",
			"LMLMLMLMM",
			"3 3 E",
			"MMRMMRMRRM"
		};
		
		for (String command: commands) {
			String[] splittedCommand = command.split(" ");
			if (splittedCommand != null) {
				if (splittedCommand.length == 2) {
					plateau = new Plateau(Integer.valueOf(splittedCommand[0]), Integer.valueOf(splittedCommand[1]));
				} else if (splittedCommand.length == 3) {
					if (rovers == null) {
						rovers = new ArrayList<Rover>();
					}
					rovers.add(new Rover(plateau, Integer.valueOf(splittedCommand[0]), Integer.valueOf(splittedCommand[1]), splittedCommand[2]));
				} else {
					Rover rover = rovers.get(rovers.size() - 1);
					for (char c: command.toCharArray()) {
						rover.move(c);
					}
					System.out.println(rover.location());
				}
			}
		}
	}
	
}
