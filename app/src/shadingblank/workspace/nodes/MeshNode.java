package shadingblank.workspace.nodes;

import shadingblank.rendering.Mesh;
import shadingblank.rendering.ShaderProgram;

import org.joml.Matrix4f;

public class MeshNode extends Node{

	public static final String type = "Mesh";

	private static final Mesh customMesh = new Mesh("mesh.obj", new float[]{
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0
		}, new int[]{
			0, 1, 2, 2, 3, 0
		});

	private Mesh mesh;

	private Matrix4f model;

	public final Attribute3f position;
	public final Attribute3f scale;
	public final Attribute3f angle; 

	public ShaderAttribute shader;

	public MeshNode(String name) {
		super(name);

		model = new Matrix4f();

		position = new Attribute3f("position", this, 0, 0, 0);
		scale = new Attribute3f("scale", this, 1, 1, 1);
		angle = new Attribute3f("angle", this, 0, 0, 0);

		shader = new ShaderAttribute("shader", this);

		this.mesh = customMesh;
	}

	public MeshNode(String name, Mesh mesh) {
		super(name);

		model = new Matrix4f();

		position = new Attribute3f("position", this, 0, 0, 0);
		scale = new Attribute3f("scale", this, 1, 1, 1);
		angle = new Attribute3f("angle", this, 0, 0, 0);

		this.mesh = mesh;
	}

	public MeshNode(String name, Mesh mesh, boolean removable) {
		this(name, mesh);
		this.removable = removable;
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

		model.rotateX((float)Math.toRadians(angle.getX()));
		model.rotateY((float)Math.toRadians(angle.getY()));
		model.rotateZ((float)Math.toRadians(angle.getZ()));

		model.scale(scale.getX(), scale.getY(), scale.getZ());

		return model;
	}

	// -------- shaders --------



}