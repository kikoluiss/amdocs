

import java.util.ArrayList;
import java.util.List;

import entities.Card;
import entities.Circuit;
import entities.Link;
import entities.Node;
import entities.Port;

public class Network {
	
	private int nodeId = 0;
	private int linkId = 0;
	private int circuitId = 0;
	
	private List<Node> nodes;
	private List<Link> links;
	private List<Circuit> circuits;
	
	public void addNode(int phisicalPorts) {
		addNode(phisicalPorts, null);
	}

	public void addNode(int phisicalPorts, Card[] cards) {
		nodeId += 1;
		Node node = new Node(nodeId, phisicalPorts, cards);
		
		if (nodes == null) {
			nodes = new ArrayList<Node>();
		}
		
		nodes.add(node);
	}

	public Node getNode(int index) {
		return nodes.get(index);
	}
	
	public void linkNodes(Node startNode, Node endNode) {
		
		try {
			int startPortIndex = startNode.getAvailablePort();
			Port startPort = startNode.usePort(startPortIndex);
			
			int endPortIndex = endNode.getAvailablePort();
			Port endPort = endNode.usePort(endPortIndex);
			
			linkId += 1;
			Link link = new Link(linkId, startPort, endPort);
			
			if (links == null) {
				links = new ArrayList<Link>();
			}
			
			links.add(link);
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	public Link getLink(int index) {
		return links.get(index);
	}
	
	public void createCircuit(List<Link> links) {
		circuitId += 1;
		
		Circuit circuit = new Circuit(circuitId, links); 

		if (this.circuits == null) {
			this.circuits = new ArrayList<Circuit>();
		}
		
		this.circuits.add(circuit);
	}
		
	public void showNetworkStructure() {
		if (nodes != null && nodes.size() > 0) {
			System.out.print("Nodes: ");
			for (Node node: nodes) {
				System.out.print(String.format("%s, ", node.getName()));
			}
			System.out.println();
		}

		if (links != null && links.size() > 0) {
			System.out.print("Links: ");
			for (Link link: links) {
				System.out.print(String.format("%s: %s, ", link.getName(), link.getLinkedNodes()));
			}
			System.out.println();
		}
		
		if (circuits != null && circuits.size() > 0) {
			System.out.print("Circuits: ");
			for (Circuit circuit: circuits) {
				System.out.print(String.format("%s: %s, ", circuit.getName(), circuit.getNodesInCircuit()));
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Network network = new Network();
		
		network.addNode(2);
						
		network.addNode(2);

		network.addNode(2);

		
		network.linkNodes(network.getNode(0), network.getNode(1));

		network.linkNodes(network.getNode(1), network.getNode(2));

		
		network.createCircuit(new ArrayList<Link>() {{ 
			add(network.getLink(0));
		}});

		network.createCircuit(new ArrayList<Link>() {{ 
			add(network.getLink(1));
		}});

		network.createCircuit(new ArrayList<Link>() {{ 
			add(network.getLink(0));
			add(network.getLink(1));
		}});

		network.showNetworkStructure();
	}
}
