package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static Stage stage;
	
	public static Parent root;
	
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
		initalStage.setFullScreen(true);
		initalStage.show();
		
		stage = initalStage;
	}
	
	public static void swapScenes(Parent newScene){
	    stage.getScene().setRoot(newScene);
	}
	
	public static ResultSet executeQuerys(String currentQuery) throws IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		};
		
		try {
		
			Connection conection = DriverManager.getConnection(
					System.getenv("URL_SQL"),
					System.getenv("UNAME"),
					System.getenv("PASSWORD")
			);
			
			Statement statement = conection.createStatement();
			
			ResultSet result = statement.executeQuery(currentQuery);
				
			if (result.next()) {
				return result;
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		};
		return null;
	}
	
	public static int executeUpdates(String currentQuery) throws IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		};
		
		try {
		
			Connection conection = DriverManager.getConnection(
					System.getenv("URL_SQL"),
					System.getenv("UNAME"),
					System.getenv("PASSWORD")
			);
			
			Statement statement = conection.createStatement();
			
			int result = statement.executeUpdate(currentQuery);
				
			if (result != 0) {
				return result;
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		};
		return 0;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
