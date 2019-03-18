package entities;

public class Node {
	int id;
	String name;
	Port[] ports;
	Card[] cards;
	
	public Node(int id, int phisicalPorts, Card[] cards) {
		this.id = id;
		this.name = String.format("Node_%d", id);
		this.ports = new Port[phisicalPorts];
		this.cards = cards;
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
		throw new Error("No ports available");
	}
	
	public Port usePort(int index) throws Error {
		if (index < ports.length) {
			ports[index] = new Port(this);
			return ports[index];
		} else {
			throw new Error(String.format("No port availabel at index %d", index));
		}
	}
	
}
