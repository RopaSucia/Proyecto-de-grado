package shadingblank.workspace.nodes;

import org.joml.Vector4f;

public class Attribute4f extends NodeAttribute {

	private float [] value;

	public Attribute4f(String name, Node node, float x, float y, float z, float w) {
		super("04f-" + name, node);
		value = new float[] {x, y, z, w};
	}

	public Attribute4f(String name, Node node, float [] vector) {
		super("04f-" + name, node);

		if(vector.length >= 4) {
			value = vector;
		} else {
			vector = new float[] {1, 1, 1, 1};
		}
	}


	public Attribute4f(String name, Node node, Vector4f vector) {
		this(name, node, vector.x, vector.y, vector.z, vector.w);
	}

	public void get(float [] arr) {
		if(arr.length >= 4) {
			arr[0] = value[0];
			arr[1] = value[1];
			arr[2] = value[2];
			arr[3] = value[3];
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

	public void setW(float w) {
		value[3] = w;
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

	public void addW(float w) {
		value[3] += w;
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

	public float getW() {
		return value[3];
	}
}