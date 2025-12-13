package shadingblank.nodes;

public class Attribute1f extends NodeAttribute{

	public float value;

	public Attribute1f(String name, Node node, float value) {
		super("01f-" + name, node);
		this.value = value;
	}
}