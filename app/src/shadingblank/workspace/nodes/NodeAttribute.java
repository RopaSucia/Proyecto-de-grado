package shadingblank.nodes;

public abstract class NodeAttribute {

	public final Node node;
	public final String name;

	public NodeAttribute(String name, Node node) {
		this.name = name;
		this.node = node;

		try {
			node.addAttribute(this);
		} catch(DuplicateNameAttributeException e){
			System.err.println(e.getMessage());
		}

	}
}