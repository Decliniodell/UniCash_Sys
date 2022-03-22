package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginScreenController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField textAccount;

	@FXML
	private TextField textPassword;

	@FXML
	private Label textLoading;
	
	public void stageCreate(ActionEvent event) {
		scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
//	public void switchToRegiterScreen(ActionEvent value) throws IOException {
//		root = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
//		stageCreate(value);
//	}
	
	public void switchToHomeScreen(ActionEvent value) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		stageCreate(value);
	}
	
	public void makeLogin(ActionEvent event) {
		textLoading.setVisible(true);
		
//		Thread newThread = new Thread(() -> {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			};
			
			try {
	
				String currentQuery = String.format(
						"select * from users where id_account = %s and password = md5(%s)",
						textAccount.getText(),
						textPassword.getText()
				);
			
				Connection conection = DriverManager.getConnection(System.getenv("URL_SQL"), System.getenv("UNAME"), System.getenv("PASSWORD"));
				Statement statement = conection.createStatement();
				ResultSet result = statement.executeQuery(currentQuery);
					
				if (result.next()) {
					System.out.println("aprovado");
					switchToHomeScreen(event);
				} else {
					System.out.println("noop");
				};
			
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			};
//		});
//		newThread.start();
	}
}
