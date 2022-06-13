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
	
	@FXML
	private TextField textAccount;

	@FXML
	private TextField textPassword;
	
	public void switchToHomeScreen(ActionEvent value) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		Main.swapScenes(Main.root);
	}
	
	public void switchToRegiterScreen(ActionEvent value) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
		Main.swapScenes(Main.root);
	}
	
	public void verifyFields(ActionEvent event) throws IOException, InterruptedException, SQLException {
		String accountId = textAccount.getText();
		String password = textPassword.getText();
		
		if (accountId != "" && password != "") {
			makeLogin(event, accountId, password);
			
		} else {
			
		}
	}
	
	public void makeLogin(ActionEvent event, String accountId, String password) throws IOException, SQLException {
		String currentQuery = String.format(
				"select * from users where id_account = %s and password = md5(%s)",
				accountId,
				password
		);

		ResultSet result = Main.executeQuerys(currentQuery);
		
		if (result != null ) {
			User.cpf = result.getString("cpf");
			User.name = result.getString("name");
			User.balance = result.getFloat("balance");
			User.accountId = accountId;
			User.password = password;
			switchToHomeScreen(event);
		} else {
			System.out.println("noop");
		}
	}
}
