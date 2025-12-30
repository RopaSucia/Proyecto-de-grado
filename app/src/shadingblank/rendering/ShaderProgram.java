package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

public class ShaderProgram {

	private static ShaderProgram currentProgram;

	private int programID;

	private Shader vertexShader;
	private Shader fragmentShader;
	private Shader geometryShader;

	private int vertexID, fragmentID, geometryID;

	public ShaderProgram(Shader vertex, Shader fragment, Shader geometry) {

		programID = glCreateProgram();

		if (vertex != null && vertex.getType() == Shader.VERTEX_SHADER) {
			vertexShader = vertex;
			bindShaderToProgram(vertexShader);
		}
		if (fragment != null && fragment.getType() == Shader.FRAGMENT_SHADER) {
			fragmentShader = fragment;
			bindShaderToProgram(fragmentShader);
		}
		if (geometry != null && geometry.getType() == Shader.GEOMETRY_SHADER) {
			geometryShader = geometry;
			bindShaderToProgram(geometryShader);
		}
	}

	public void use() {
		if(currentProgram != this) {
			currentProgram = this;
			glUseProgram(programID);			
		}
	}

	public void bindShaderToProgram(Shader shader) {
		switch (shader.getType()) {
			case Shader.VERTEX_SHADER:
				if (shader.getID() != vertexID) {
					glDetachShader(programID, vertexID);
					vertexID = shader.getID();
					glAttachShader(programID, vertexID);
				}
				break;
			case Shader.FRAGMENT_SHADER:
				if (shader.getID() != fragmentID) {
					glDetachShader(programID, fragmentID);
					fragmentID = shader.getID();
					glAttachShader(programID, fragmentID);
				}
				break;
			case Shader.GEOMETRY_SHADER:
				if (shader.getID() != geometryID) {
					glDetachShader(programID, geometryID);
					geometryID = shader.getID();
					glAttachShader(programID, geometryID);
				}
				break;
		}
		glLinkProgram(programID);
	}

	public void uniformMat4(String uName, Matrix4f mat) {
		glUniformMatrix4fv(glGetUniformLocation(programID, uName), false, mat.get(new float[16]));
	}

	// -------- VEC4 --------

	public void uniformVector4(String uName, Vector4f vector) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(4);
		glUniform4fv(glGetUniformLocation(programID, uName), vector.get(buff));
	}

	public void uniformVector4(String uName, float x, float y, float z, float w) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(4);
		buff.put(new float[] {x, y, z, w});
		glUniform4fv(glGetUniformLocation(programID, uName), buff);
	}

	// -------- VEC3 --------

	public void uniformVector3(String uName, Vector3f vector) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(3);
		glUniform3fv(glGetUniformLocation(programID, uName), vector.get(buff));
	}

	public void uniformVector3(String uName, float x, float y, float z) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(3);
		buff.put(new float[] {x, y, z});
		glUniform3fv(glGetUniformLocation(programID, uName), buff);
	}

	// -------- VEC2 --------

	public void unfiromVector2(String uName, Vector2f vector) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(2);
		glUniform2fv(glGetUniformLocation(programID, uName), vector.get(buff));
	}

	public void uniformVector2(String uName, float x, float y) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(2);
		buff.put(new float[] {x, y});
		glUniform2fv(glGetUniformLocation(programID, uName), buff);
	}

	// -------- FLOAT --------

	public void uniformFloat(String uName, float num) {
		glUniform1f(glGetUniformLocation(programID, uName), num);
	}

	// -------- INT --------

	public void uniformInt(String uName, int num) {
		glUniform1i(glGetUniformLocation(programID, uName), num);
	}
}