package entities;

public class Card {
	int id;
	String name;
	Port[] ports;

	public Card(int id, int phisicalPorts) {
		this.id = id;
		this.name = String.format("Card_%d", id);
		this.ports = new Port[phisicalPorts];
	}
	
	public String getName() {
		return name;
	}

	public int getAvailablePort() throws Error {
		for (int i = 0; i < ports.length; i++) {
			if (ports[i] == null) {
				return i;
			}
		}
		return -1;
	}

}
