package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

public class Shader extends Resource{

	public static final int 
		VERTEX_SHADER = GL_VERTEX_SHADER,
		FRAGMENT_SHADER = GL_FRAGMENT_SHADER,
		GEOMETRY_SHADER = GL_GEOMETRY_SHADER;

	private int shaderID, lastID, type;

	public Shader(String name, String source, int type) {
		super(name);
		this.type = type;
		setShaderSource(source);
	}

	private void setShaderSource(String src) {
		if(shaderID != 0) {
			lastID = shaderID;
		} 
		shaderID = glCreateShader(type);

		glShaderSource(shaderID, src);
		glCompileShader(shaderID);
	}

	public int getType() {
		return type;
	}

	public int getID() {
		return shaderID;
	}

	@Override
	public int getReference () {
		return 1;
	}

}