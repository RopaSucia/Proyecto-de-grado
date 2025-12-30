package shadingblank;

import shadingblank.rendering.*;
import shadingblank.workspace.Workspace;
import shadingblank.workspace.ui.*;
import shadingblank.workspace.ui.modules.FileExplorerDialog;

import shadingblank.nodes.CameraNode;
import shadingblank.nodes.ViewportNode;
import shadingblank.nodes.MeshNode;

public class Launcher extends Motor{

	public static Launcher instance;

	// -------- SOLO INICIALIZAR VARIABLES DENTRO DEL CONTRUCTOR O EL MÉTODO INIT() --------

	Mesh mesh;

	FrameBuffer frame;
	ViewportNode frameNode;

	Shader vertexShader;
	Shader fragmentShader;
	ShaderProgram program;

	MeshNode meshN;
	CameraNode camera;

	Texture tex, texFer;

	double[] mousePos;
	double[] lastMousePos;

	String vertexSrc;
	String fragmentSrc;

	String imagen;

	public FileExplorerDialog explorer;

	public Workspace workspace;

	public Launcher() {
		super();
	}

	@Override
	public void init() {

		instance = this;

		title = "ShadingBlank";

		vertexSrc = FilesManager.getString("resources/basicVertex.vert");
		fragmentSrc = FilesManager.getString("resources/basicFragment.frag");

		imagen = "C:\\Users\\USER\\Desktop\\ShadingBlank\\app\\resources\\Captura.PNG";

		mesh = new Mesh("mesh.obj", new float[]{
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0
		}, new int[]{
			0, 1, 2, 2, 3, 0
		});
		backgroundColor(0, 0, 1, 1);

		FilesManager.getDirectoryFiles("C:\\Users\\USER\\Desktop");

		vertexShader = new Shader("basicVertex.vert", vertexSrc, Shader.VERTEX_SHADER);

		fragmentShader = new Shader("basicFragment.frag", fragmentSrc, Shader.FRAGMENT_SHADER);


		program = new ShaderProgram(vertexShader, fragmentShader, null);
		program.use();

		render.addMesh(mesh);

		workspace = new Workspace();

		MainSpace space = new MainSpace();

		explorer = new FileExplorerDialog();
		explorer.directory("C:\\Users\\USER\\Desktop");
		scene.nodes.createNode("nodoWebon");

		tex = new Texture("Advance-War", imagen);
		texFer = new Texture("Uyama-Hiroto", "C:\\Users\\USER\\Desktop\\J\\500x500.jpg");

		scene.resources.add(tex);
		scene.resources.add(texFer);
		scene.resources.add(vertexShader);
		scene.resources.add(fragmentShader);
		scene.resources.add(mesh);

		frameNode =	(ViewportNode)scene.nodes.createViewportNode("custom");
		frame = frameNode.buffer;

		meshN = scene.nodes.createMeshNode("node");
		camera = scene.nodes.createCameraNode("camera");
		scene.nodes.createViewportNode("viewport");

		window.addLayer(space);
		window.addLayer(explorer);

		frame.bgColor(0, 1, 1, 1);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void loop(float delta) {
		frame.begin();

		workspace.update(delta);

		frame.close();
		window.backgroundColor(0, 0, 1, 1);

	}

	public static void main(String [] args) {

		instance = new Launcher();
	}
}