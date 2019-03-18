package entities;

public class Link {
	int id;
	String name;
	Port startPort;
	Port endPort;
	
	public Link(int id, Port startPort, Port endPort) {
		this.id = id;
		this.name = String.format("Link_%d", id);
		this.startPort = startPort;
		this.endPort = endPort;
	}
	
	public String getName() {
		return name;
	}

	public String getLinkedNodes() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.startPort.getNode().getName());
		sb.append(" <-> ");
		sb.append(this.endPort.getNode().getName());
		
		return sb.toString();
	}
}
