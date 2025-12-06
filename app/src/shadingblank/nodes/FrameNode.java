package shadingblank.nodes;

import shadingblank.rendering.FrameBuffer;

public class FrameNode extends Node{

	private FrameBuffer buffer;


	public FrameNode(String name, FrameBuffer buffer) {
		super(name);
		this.buffer = buffer;
	}

	public FrameBuffer getFrameBuffer() {
		return buffer;
	}

	public void setFrameBuffer(FrameBuffer buffer) {
		this.buffer = buffer;
	}

	

}