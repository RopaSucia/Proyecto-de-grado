package shadingblank.nodes;

import shadingblank.CurrentInstance;
import shadingblank.rendering.ShaderProgram;

public class ShaderAttribute extends NodeAttribute implements CurrentInstance{
 
	public ShaderProgram value;

	public ShaderAttribute(String name, Node node) {
		super("sha-" + name, node);
		value = instance.customProgram;
	}

}