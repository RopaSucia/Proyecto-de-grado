package shadingblank.nodes;

import shadingblank.rendering.FrameBuffer;

public class ViewportNode extends Node{

	public final FrameBuffer buffer;

	public final Attribute2f bufferSize; // Height and width buffer size
	public final ColorAttribute bgColor;

	public ViewportNode(String name, FrameBuffer buffer) {
		super(name);
		this.buffer = buffer;

		bufferSize = new Attribute2f("size", this, buffer.getXSize(), buffer.getYSize());
		bgColor = new ColorAttribute("background color", this, buffer.color);

	}

	public ViewportNode(String name) {
		this(name, new FrameBuffer(600, 800));

	}

	public ViewportNode(String name, int height, int width) {
		this(name, new FrameBuffer(height, width));
	}

	public void update() {
		buffer.resize((int)bufferSize.getY(), (int)bufferSize.getX());
	}
}