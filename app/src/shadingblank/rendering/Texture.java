package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

public class Texture extends Resource{

	public final int textureID
	;

	public final int height, width, channels;
	public final String root;
	public Texture(String name, String sourcePath) {
		super(name);		

		root = sourcePath;


		textureID = glGenTextures();

		//glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		IntBuffer x = BufferUtils.createIntBuffer(1);
		IntBuffer y = BufferUtils.createIntBuffer(1);
		IntBuffer ch = BufferUtils.createIntBuffer(1);

		ByteBuffer data = STBImage.stbi_load(sourcePath, x, y, ch, 0);

		height = y.get();
		width = x.get();
		channels = ch.get();

		int chnls = (channels == 3) ? GL_RGB:GL_RGBA;

		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, chnls, width, height, 0, chnls, GL_UNSIGNED_BYTE, data);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glGenerateMipmap(GL_TEXTURE_2D);
	}

	@Override
	public int getReference () {
		return textureID;
	}

}