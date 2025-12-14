package shadingblank.nodes;

public class ColorAttribute extends NodeAttribute{

	private float col[];

	public ColorAttribute (String name, Node node) {
		super("col-" + name, node);

		col = new float[4];
	}

	public ColorAttribute (String name, Node node, float [] col) {
		super("col-" + name, node);
		this.col = col;
	}

	public ColorAttribute (String name, Node node, float r, float g, float b, float a) {
		super("col-" + name, node);
		this.col = new float[] {r, g, b, a};
	}

	//setters

	public void setR(float r) {
		col[0] = r;
	}

	public void setG(float g) {
		col[1] = g;
	}

	public void setB(float b) {
		col[2] = b;
	}

	public void setA(float a) {
		col[3] = a;
	}

	public void setRGB(float rgb) {
		col[0] = rgb;
		col[1] = rgb;
		col[2] = rgb;
	}

	public void setRGB(float r, float g, float b) {
		col[0] = r;
		col[1] = g;
		col[2] = b;
	}

	public void setRGBA(float rgba) {
		col[0] = rgba;
		col[1] = rgba;
		col[2] = rgba;
		col[3] = rgba;
	}

	public void setRGBA(float r, float g, float b, float a) {
		col[0] = r;
		col[1] = g;
		col[2] = b;
		col[3] = a;
	}

	//getters

	public float[] get() {
		return col;
	}

	public float getR() {
		return col[0];
	}

	public float getG() {
		return col[1];
	}

	public float getB() {
		return col[2];
	}

	public float getA() {
		return col[3];
	}
}