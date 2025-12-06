package shadingblank.nodes;

import org.joml.Vector2f;

public class Attribute2f extends NodeAttribute {

	private float [] value;

	public Attribute2f(String name, Node node, float x, float y) {
		super("02f-" + name, node);
		value = new float[] {x, y};
	}

	public Attribute2f(String name, Node node, Vector2f vector) {
		this(name, node, vector.x, vector.y);
	}

	public void get(float [] arr) {
		if(arr.length >= 2) {
			arr[0] = value[0];
			arr[1] = value[1];
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

	public void addX(float x) {
		value[0] += x;
	}

	public void addY(float y) {
		value[1] += y;
	}

	public float getX() {
		return value[0];
	}

	public float getY() {
		return value[1];
	}

}