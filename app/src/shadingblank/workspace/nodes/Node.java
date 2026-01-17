package shadingblank.workspace.nodes;

public class Node {

	public static final String type = "Node";
	public final TextAttribute name;
	public boolean removable;
	
	public boolean closed = false;

	public NodeAttribute[] attributes;
	public int index = 0;

	public Node(String name) {
		this.name = new TextAttribute("name", this, name);
		removable = true;
	}

	public Node(String name, boolean removable) {
		this.name = new TextAttribute("name", this, name);
		this.removable = removable;
	}

	public void addAttribute(NodeAttribute attribute) throws DuplicateNameAttributeException {

		if(attributes == null) {
			attributes = new NodeAttribute[1];
		}

		if (attributes.length == index) {

			NodeAttribute[] newAttList = new NodeAttribute[attributes.length + 1];
			for (int i = 0; i < attributes.length; i++) {
				newAttList[i] = attributes[i];
			}
			newAttList[attributes.length] = attribute;
			attributes = newAttList;
			index ++;
		} else if(attributes.length != index) {
			attributes[index] = attribute;
			index ++;
		}

		 else {
			throw new DuplicateNameAttributeException(attribute.name + " ya existe en el nodo " + name);
		}
	}

	public NodeAttribute getAttribute(String name) {
		for (NodeAttribute att : attributes) {
			if (att.name == name) {
				return att;
			}
		}
		return null;
	}
}