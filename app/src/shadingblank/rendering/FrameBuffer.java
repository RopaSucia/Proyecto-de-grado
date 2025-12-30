package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

import java.nio.*;

public class FrameBuffer {

	private int frameID, renderID, textureID;

	private int height, width;

	public float [] size;
	public float [] color;

	public boolean depthBuffer = true;
	public boolean stencilBuffer = true;

	public FrameBuffer(int height, int width) {

		size = new float[2];
		color = new float[4];

		resize(height, width);
	
	}

	// --------Begin and End attach functions--------

	// begin with the drawing operations on this buffer

	public void begin() {
		glBindFramebuffer(GL_FRAMEBUFFER, frameID);

		glEnable(GL_DEPTH_TEST);
		glEnable(GL_STENCIL_TEST);
		glClearBufferfv(GL_COLOR, 0, color);
		glClear(GL_DEPTH_BUFFER_BIT);	
		glViewport(0, 0, width, height);
	}

	// finish with the drawing operations on this buffer

	public void close() {
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	//--------setters--------

	//set buffer size

	private void resize(boolean AAMS, int height, int width) {
		if (!AAMS) {
			frameID = glGenFramebuffers();
			glBindFramebuffer(GL_FRAMEBUFFER, frameID);

			textureID = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, textureID);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer)null);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);	

			renderID = glGenRenderbuffers();
			glBindRenderbuffer(GL_RENDERBUFFER, renderID);
			glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, width, height);

			//enlaza los dos bloques de memoria al render buffer
			glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, textureID, 0);
			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderID);
			close();
		}
	}

	public void resize(int height, int width) {
		if(this.height != height || this.width != width) {
			resize(false, height, width);
			this.height = height;
			this.width = width;

			size[0] = this.height;
			size[1] = this.width;
		}
	}

	//set color size

	public void bgColor(float r, float g, float b, float a) {
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;
	}

	//--------getters--------

	public int getXSize() {
		return width;
	}

	public int getYSize() {
		return height;
	}

	public int getTexture() {
		return textureID;
	}
}