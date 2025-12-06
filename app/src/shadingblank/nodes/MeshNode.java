package shadingblank.nodes;

import shadingblank.rendering.Mesh;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MeshNode extends Node{

	public static final String type = "Mesh";

	private Mesh mesh;

	private Matrix4f model;

	private Vector3f pos;

	public float scaleX = 1;
	public float scaleY = 1;
	public float scaleZ = 1;

	public MeshNode(String name, Mesh mesh) {
		super(name);

		model = new Matrix4f();
		pos = new Vector3f();

		this.mesh = mesh;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public void setPos(float x, float y, float z) {
		pos.set(x, y, z);
	}

	public void move(float x, float y, float z) {
		pos.add(x, y, z);
	}

	public Matrix4f getModelMatrix() {

		model.identity();
		model.translate(pos);
		model.scale(scaleX, scaleY, scaleZ);

		return model;
	}
}