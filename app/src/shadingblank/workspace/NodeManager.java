package shadingblank.workspace;

import java.util.List;
import java.util.ArrayList;

import shadingblank.nodes.MeshNode;
import shadingblank.nodes.Node;

public class NodeManager {

	public final List<Node> nodes;

	private int index;

	public NodeManager() {
		nodes = new ArrayList<>();
	}

	private NodeManager setNode(Node node) {
		nodes.add(node);
		return this;
	}

	public Node getNode(String name) {
		Node node = null;
		for(Node nd: nodes) {
			if(nd.name.value == name) {
				node = nd;
			}
		}
		return node;
	}

	public Node createNode(String name) {
		Node node = new Node(name + "-node-" + index);
		index ++;
		nodes.add(node);
		return node;
	}

	public MeshNode createMeshNode() {
		MeshNode node = new MeshNode("meshNode-" + index, null);
		index ++;
		nodes.add(node);
		return node;
	}

	public void add(Node node) {
		nodes.add(node);
	}

}