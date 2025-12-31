package shadingblank.workspace;

import java.util.List;
import java.util.ArrayList;

import shadingblank.nodes.CameraNode;
import shadingblank.nodes.MeshNode;
import shadingblank.nodes.Node;
import shadingblank.nodes.ViewportNode;

public class NodeManager{

	public final List<Node> nodes;
	public final List<Node> blackList;

	public final List<ViewportNode> viewportNodes;
	public final List<CameraNode> cameraNodes;
	public final List<MeshNode> meshNodes;

	private int index;

	public NodeManager(){
		nodes = new ArrayList<>();
		blackList = new ArrayList<>();

		meshNodes = new ArrayList<>();
		viewportNodes = new ArrayList<>();
		cameraNodes = new ArrayList<>();

	}

	public Node getNode(String name) {
		Node node = null;
		for (Node nd : nodes) {
			if (nd.name.value == name) {
				node = nd;
			}
		}
		return node;
	}

	public Node createNode(String name) {
		Node node = new Node(name + "-node-" + index);
		index++;
		nodes.add(node);
		return node;
	}

	public CameraNode createCameraNode(String name) {
		CameraNode node = new CameraNode(name + "-cameraNode-" + index, new float[] { 200, 200 });
		cameraNodes.add(node);
		index++;
		nodes.add(node);
		return node;
	}

	public MeshNode createMeshNode(String name) {
		MeshNode node = new MeshNode(name + "meshNode-" + index);
		meshNodes.add(node);
		index++;
		nodes.add(node);
		return node;
	}

	public ViewportNode createViewportNode(String name) {
		ViewportNode node = new ViewportNode(name + "-viewportNode-" + index);
		viewportNodes.add(node);
		index++;
		nodes.add(node);
		return node;
	}

	public void deleteNode(Node node) {
		nodes.remove(node);
	}

	public void deleteNode(int index) {
		nodes.remove(index);
	}

	public void blackList(Node node) {
		blackList.add(node);
	}

	public void clearBlackList() {
		if (blackList.size() > 0) {
			nodes.removeAll(blackList);
			blackList.clear();
		}
	}
}