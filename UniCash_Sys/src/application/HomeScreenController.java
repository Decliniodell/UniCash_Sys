package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreenController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void stageCreate(ActionEvent event) {
		scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToLoginScreen(ActionEvent value) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		stageCreate(value);
	}

}
