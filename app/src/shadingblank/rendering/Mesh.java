package shadingblank.rendering;

import static org.lwjgl.opengl.GL33.*;

import org.lwjgl.BufferUtils;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;

public class Mesh extends Resource{

	public int [] VAOs = new int[1024];
	public int indices;

	private int VAO, EBO, VBO;

	public Mesh(String name, float [] vertices, int [] indices) {

		super(name);

		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices).flip();
		IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices).flip();

		this.indices = indices.length;

		//aqui configuro los buffers de vertices y los
		//indicies de estos dentro de la memoria gewstionada por OpenGL

		VAO = glGenVertexArrays();
		glBindVertexArray(VAO);

		VBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

		EBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 3*4, 0);

	}

	public void draw() {
		glDrawElements(GL_TRIANGLES, indices, GL_UNSIGNED_INT, 0);
	}

	@Override
	public int getReference () {
		return 1;
	}

}