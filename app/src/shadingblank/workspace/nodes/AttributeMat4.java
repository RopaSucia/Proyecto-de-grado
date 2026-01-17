package shadingblank.workspace.nodes;

import org.joml.Matrix4f;

public class AttributeMat4 extends NodeAttribute {

	public static final int size = 16;

	private final Matrix4f mat;

	public AttributeMat4(String name, Node node, Matrix4f mat) {
		super("16f-" + name, node);

		this.mat = (mat != null) ? mat:new Matrix4f();
	}

	public void get(float [] arr) {
		mat.get(arr);
	}

	public Matrix4f get() {
		return mat;
	}

}