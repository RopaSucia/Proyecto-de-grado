package shadingblank.nodes;

public class TextAttribute extends NodeAttribute{

	public String value;

	public TextAttribute(String name, Node node, String text) {
		super("txt-" + name, node);
		value = (text != null) ? text:"-";
	}

}