package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public void start(Stage initalStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets()
		.add(
				getClass()
				.getResource("application.css")
				.toExternalForm()
		);
		initalStage.setTitle("UniCash_Sys");
		initalStage.setScene(scene);
		initalStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
