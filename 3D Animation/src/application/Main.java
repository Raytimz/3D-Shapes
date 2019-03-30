package application;
	
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;


public class Main extends Application { 
	@Override 
	public void start(Stage primaryStage) { 
				
		TriangleMesh msh = new TriangleMesh();
		// Vertex coordinates
		float h = 210;    // Height (Y)
		float w = 245;    // Width (X)
		float d = 125;    // Depth (Z)

		 msh.getPoints().addAll(
		    0,      -h / 2,   0,        // 0
		    w / 2,  h / 2,    d / 2,    // 1
		    w / 2,  h / 2,    -d / 2,   // 2
		    -w / 2, h / 2,    -d / 2,   // 3
		    -w / 2, h / 2,    d / 2     // 4
		); 
		 		
		// Add texture coordinates
		 msh.getTexCoords().addAll(
		         0.504f, 0.524f,     // 0
		         0.701f, 0,          // 1
		         0.126f, 0,          // 2
		         0,      0.364f,     // 3
		         0,      0.608f,     // 4
		         0.165f, 1,          // 5
		         0.606f, 1,          // 6
		         0.575f, 0.420f,     // 7
		         0.575f, 0.643f,     // 8
		         0.740f, 0.643f,     // 9
		         0.740f, 0.420f      // 10      
		 );
		 
		 msh.getFaces().addAll(
			        0, 0, 3, 5, 2, 6, // Front face
			        0, 0, 2, 2, 1, 3, // Right face
			        0, 0, 1, 1, 4, 2, // Back face
			        0, 0, 4, 4, 3, 5, // Left right face
			        2, 9, 3, 8, 4, 7, // Bottom face
			        2, 9, 4, 7, 1, 10 // Bottom face
			);
		
		PhongMaterial mt = new PhongMaterial(); 
		mt.setDiffuseColor(Color.RED); 
		 		
		MeshView pyramid = new MeshView(msh);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setMaterial(mt);
		
		pyramid.setTranslateX(550);
		pyramid.setTranslateY(150); 
		pyramid.setTranslateZ(50);
		
		Cylinder box = new Cylinder();  
		box.setHeight(150); 
		box.setRadius(50);
		
		box.setMaterial(mt);
		
		box.setTranslateX(350); 
		box.setTranslateY(150); 
		box.setTranslateZ(50);
		
		RotateTransition rt = new RotateTransition(); 
		rt.setDuration(Duration.millis(1000)); 
		rt.setAxis(Rotate.X_AXIS);
		rt.setByAngle(360); 
		rt.setCycleCount(550); 
		rt.setNode(box); 
		rt.setNode(pyramid);
		
		RotateTransition rt1 = new RotateTransition(); 
		rt1.setDuration(Duration.millis(1000)); 
		rt1.setAxis(Rotate.Z_AXIS);
		rt1.setByAngle(360); 
		rt1.setCycleCount(550); 
		rt1.setNode(box); 
		rt1.setNode(pyramid);
		
		Button btn = new Button(); 
		btn.setText("Start"); 
		btn.setLayoutX(150); 
		btn.setLayoutY(200);
		
		Button btnS = new Button(); 
		btnS.setText("Stop"); 
		btnS.setLayoutX(100); 
		btnS.setLayoutY(200);
		
		EventHandler<javafx.scene.input.MouseEvent> evh = 
		new EventHandler<javafx.scene.input.MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
					rt.play(); 
					rt1.play();
				} 
			}; 
		btn.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, evh); 
		
		EventHandler<javafx.scene.input.MouseEvent> evh1 = 
				new EventHandler<javafx.scene.input.MouseEvent>() { 
				@Override 
				public void handle(MouseEvent e) { 
						rt.stop(); 
						rt1.stop();
					} 
				};
		btnS.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, evh1); 
		
		Group root = new Group(box,btn,btnS,pyramid); 
		Scene scene = new Scene(root,600,300); 
		
		PerspectiveCamera camera = new PerspectiveCamera(); 
		camera.setTranslateX(0); 
		camera.setTranslateY(0); 
		camera.setTranslateZ(150); 
		scene.setCamera(camera); 
		
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		
		} 
	
	public static void main(String[] args) { 
	launch(args); 
	} 
}