package shadingblank.workspace.nodes;

import org.joml.Vector3f;

public class Attribute3f extends NodeAttribute {

	private float [] value;

	public Attribute3f(String name, Node node, float x, float y, float z) {
		super("03f-" + name, node);
		value = new float[] {x, y, z};
	}

	public Attribute3f(String name, Node node, float [] vector) {
		super("03f-" + name, node);

		if(vector.length >= 3) {
			value = vector;
		} else {
			vector = new float[] {1, 1, 1};
		}
	}

	public Attribute3f(String name, Node node, Vector3f vector) {
		this(name, node, vector.x, vector.y, vector.z);
	}

	public void get(float [] arr) {
		if(arr.length >= 3) {
			arr[0] = value[0];
			arr[1] = value[1];
			arr[2] = value[2];
		}
	}

	public float [] get() {
		return value;
	}

	public float [] getArray() {
		return value;
	}

	public void setX(float x) {
		value[0] = x;
	}

	public void setY(float y) {
		value[1] = y;
	}

	public void setZ(float z) {
		value[2] = z;
	}

	public void addX(float x) {
		value[0] += x;
	}

	public void addY(float y) {
		value[1] += y;
	}

	public void addZ(float z) {
		value[2] += z;
	}
 
	public float getX() {
		return value[0];
	}

	public float getY() {
		return value[1];
	}

	public float getZ() {
		return value[2];
	}

}