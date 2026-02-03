package shadingblank;

import shadingblank.rendering.*;
import shadingblank.workspace.Workspace;
import shadingblank.workspace.ui.*;
import shadingblank.workspace.ui.modules.FileExplorerDialog;

import shadingblank.workspace.nodes.CameraNode;
import shadingblank.workspace.nodes.ViewportNode;
import shadingblank.workspace.nodes.MeshNode;

public class Launcher extends Motor{

	public static Launcher instance;

	// -------- SOLO INICIALIZAR VARIABLES DENTRO DEL CONTRUCTOR O EL MÉTODO INIT() --------

	Mesh mesh;

	FrameBuffer frame;
	ViewportNode frameNode;

	Shader vertexShader;
	Shader fragmentShader;
	public ShaderProgram customProgram;

	Texture tex, texFer;

	double[] mousePos;
	double[] lastMousePos;

	String vertexSrc;
	String fragmentSrc;

	String imagen;

	public Explorer explorer;

	public Workspace workspace;

	public UIRoot UIroot;

	public Launcher() {
		super();
	}

	@Override
	public void init() {

		instance = this;

		title = "ShadingBlank";

		vertexSrc = files.getString("resources/basicVertex.vert");
		fragmentSrc = files.getString("resources/basicFragment.frag");

		imagen = "C:\\Users\\USER\\Desktop\\ShadingBlank\\app\\resources\\Captura.PNG";

		MeshLoader ldr = new MeshLoader();
		mesh = ldr.load("C:\\Users\\USER\\Desktop\\ShadingBlank\\app\\resources\\Utah.stl")[0];

		backgroundColor(0, 0, 1, 1);

		files.getDirectoryFiles("C:\\Users\\USER\\Desktop");

		vertexShader = new Shader("basicVertex.vert", vertexSrc, Shader.VERTEX_SHADER);

		fragmentShader = new Shader("basicFragment.frag", fragmentSrc, Shader.FRAGMENT_SHADER);

		customProgram = new ShaderProgram(vertexShader, fragmentShader, null);
		customProgram.use();

		workspace = new Workspace();

		UIroot = new UIRoot();

		explorer = new Explorer();
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

		scene.nodes.createMeshNode("node");

		scene.nodes.createCameraNode("camera");
		scene.nodes.createViewportNode("viewport");

		window.addLayer(UIroot);
		
		frame.bgColor(0, 1, 1, 1);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void loop(float delta) {

		workspace.update(delta);
		window.backgroundColor(0, 0, 0, 0);

	}

	public static void main(String [] args) {

		instance = new Launcher();
	}
}