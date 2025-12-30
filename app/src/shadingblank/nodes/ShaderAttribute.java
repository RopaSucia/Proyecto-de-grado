package shadingblank.nodes;

import shadingblank.rendering.ShaderProgram;

public class ShaderAttribute extends NodeAttribute{

	public ShaderProgram value;

	public ShaderAttribute(String name, Node node) {
		super("sha-" + name, node);
	}

}