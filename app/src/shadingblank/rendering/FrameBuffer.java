package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

import java.nio.*;
import org.lwjgl.BufferUtils;

public class FrameBuffer {

	private int frameID, renderID, textureID;
	private float r, g, b, a;

	private int height = 600, width = 800;

	public boolean depthBuffer = true;
	public boolean stencilBuffer = true;

	public FrameBuffer(int height, int width) {
		this.height = height;
		this.width = width;
		
		newFrameBuffer(false, height, width);
	}

	public void begin() {
		glBindFramebuffer(GL_FRAMEBUFFER, frameID);

		FloatBuffer colors = BufferUtils.createFloatBuffer(4)
		.put(new float[]{r, g, b, a}).flip();

		glEnable(GL_DEPTH_TEST);
		glEnable(GL_STENCIL_TEST);
		glClearBufferfv(GL_COLOR, 0, colors);
		glClear(GL_DEPTH_BUFFER_BIT);	
		glViewport(0, 0, width, height);
	}

	public void close() {
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	private void newFrameBuffer(boolean AAMS, int height, int width) {
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

	public void backgroundColor(float r, float g, float b, float a) {
		this.r = r; this.g = g; this.b = b; this.a = a;
	}

	public void setViewport(int height, int width) {
		this.height = height;
		this.width = width;
		newFrameBuffer(false, height, width);
	}

	public int getTexture() {
		return textureID;
	}
}