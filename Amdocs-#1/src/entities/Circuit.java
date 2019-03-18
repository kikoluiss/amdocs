package entities;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
	int id;
	String name;
	Port startPort;
	Port endPort;
	List<Node> nodes;
	
	public Circuit(int id, List<Link> links) {
		this.id = id;
		this.name = String.format("Circuit_%d", id);
		this.nodes = new ArrayList<Node>();
		this.startPort = links.get(0).startPort;
		this.endPort = links.get(links.size() - 1).endPort;
		for (Link link: links) {
			if (!this.nodes.contains(link.startPort.node) ) {
				this.nodes.add(link.startPort.node);
			}
			if (!this.nodes.contains(link.endPort.node)) {
				this.nodes.add(link.endPort.node);
			}			
		}
		
	}
	
	public String getName() {
		return name;
	}

	public String getNodesInCircuit() {
		StringBuilder sb = new StringBuilder();
		for (Node node: nodes) {
			if (sb.length() > 0) {
				sb.append(" - ");
			}
			sb.append(node.getName());
		}		
		return sb.toString();

	}
}
