package shadingblank.nodes;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CameraNode extends Node {

	public static final String type = "Camera";

	private Matrix4f view, projection;

	//private Vector3f pos, angles;

	public final Attribute1f fov;

	public final Attribute3f pos;
	public final Attribute3f angles;

	private float [] size = {1f, 1f};

	public CameraNode(String name, float [] size) {
		super(name);

		this.size = size;

		view = new Matrix4f();
		projection = new Matrix4f();

		fov = new Attribute1f("fov", this, 90);

		pos = new Attribute3f("position",this, 0, 0 ,0);
		angles = new Attribute3f("angle", this, 0, 0, 0);
	}

	public void rotateX(float angle) {
		angles.addX(angle);
	}

	public void rotateY(float angle) {
		angles.addY(angle);
	}

	public void rotateZ(float angle) {
		angles.addZ(angle);
	}	

	public void rotateXYZ(float axisX, float axisY, float axisZ) {
		angles.addX(axisX);
		angles.addY(axisY);
		angles.addZ(axisZ);
	}

	public void moveX(float v) {
		pos.addX((float)Math.cos(Math.toRadians(angles.getY())) * v);
		pos.addZ((float)Math.sin(Math.toRadians(angles.getY())) * v);
	}

	public void moveZ(float v) {
		pos.addZ((float)Math.cos(Math.toRadians(angles.getY())) * (float)Math.cos(Math.toRadians(angles.getX())) * v);
		pos.addX((float)-Math.sin(Math.toRadians(angles.getY())) * (float)Math.cos(Math.toRadians(angles.getX())) * v);
		pos.addY((float)Math.sin(Math.toRadians(angles.getX())) * v);
	}

	public Matrix4f getViewMat() {
		view.identity();
		view.rotate((float)Math.toRadians(angles.getX()), 1f, 0f, 0f);
		view.rotate((float)Math.toRadians(angles.getY()), 0f, 1f, 0f);
		view.rotate((float)Math.toRadians(angles.getZ()), 0f, 0f, 1f);
		view.translate(pos.getX(), pos.getY(), pos.getZ());

		return view;
	}

	public Matrix4f getProjectionMat() {
		projection.identity();
		projection.perspective((float)Math.toRadians(fov.value), size[0]/size[1], 0.001f, 1000f);
		return projection;
	}
}