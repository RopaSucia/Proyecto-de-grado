package shadingblank.nodes;

import shadingblank.rendering.Mesh;

import org.joml.Matrix4f;

public class MeshNode extends Node{

	public static final String type = "Mesh";

	private Mesh mesh;

	private Matrix4f model;

	public Attribute3f position;

	public float scaleX = 1;
	public float scaleY = 1;
	public float scaleZ = 1;

	public MeshNode(String name, Mesh mesh) {
		super(name);

		model = new Matrix4f();
		position = new Attribute3f("position", this, 0, 0, 0);

		this.mesh = mesh;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public Matrix4f getModelMatrix() {

		model.identity();
		model.translate(position.getX(), position.getY(), position.getZ());
		model.scale(scaleX, scaleY, scaleZ);

		return model;
	}
}