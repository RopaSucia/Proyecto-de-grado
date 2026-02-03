package shadingblank.rendering;

import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;
import org.lwjgl.assimp.Assimp;

public class MeshLoader {

	private AIScene scene;

	public int numMeshes;

	public MeshLoader() {

	}

	public Mesh[] load(String path) {

		Mesh [] meshes;

		scene = Assimp.aiImportFile(path, 
			Assimp.aiProcess_Triangulate | 
            Assimp.aiProcess_FlipUVs | 
            Assimp.aiProcess_GenNormals |
            Assimp.aiProcess_FlipWindingOrder |
            Assimp.aiProcess_MakeLeftHanded 
        );

		if(scene == null) {
			throw new RuntimeException("No se ha podido cargar el modelo: " + path);
		}

		numMeshes = scene.mNumMeshes();
		PointerBuffer meshesBuffer = scene.mMeshes();

		meshes = new Mesh[numMeshes];

		for (int i = 0; i < numMeshes; i++) {
			AIMesh mesh = AIMesh.create(meshesBuffer.get());
			Mesh m = new Mesh("mesh:"+i, getVertices(mesh), getIndices(mesh));
			meshes[i] = m;
		}

		Assimp.aiReleaseImport(scene);
		return meshes;
	}

	public float [] getVertices(AIMesh mesh) {

		AIVector3D.Buffer vertices = mesh.mVertices();
		float[] vertexData = new float[vertices.remaining() * 3];

		for(int i = 0; i < vertices.remaining(); i++) {

			AIVector3D vector = vertices.get(i);

			vertexData[i * 3 + 0] = vector.x();
			vertexData[i * 3 + 1] = vector.y();
			vertexData[i * 3 + 2] = vector.z();

		}

		return vertexData;
	}

	public int [] getIndices(AIMesh mesh) {

		AIFace.Buffer faces = mesh.mFaces();
		int[] indicesData = new int[faces.remaining() * 3];

		for (int i = 0; i < faces.remaining(); i++) {
			AIFace face = faces.get(i);
			indicesData[i * 3 + 0] = face.mIndices().get(0);	
			indicesData[i * 3 + 1] = face.mIndices().get(1);	
			indicesData[i * 3 + 2] = face.mIndices().get(2);	
		}

		return indicesData;
	}

}