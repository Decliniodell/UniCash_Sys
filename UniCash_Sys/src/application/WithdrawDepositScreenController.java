package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawDepositScreenController {
	
	@FXML
	private TextField balanceValue;
	
	public void switchToHomeScreen(ActionEvent event) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		Main.swapScenes(Main.root);
	}
	
	public boolean checkAndUpdateBalance() throws IOException {
		
		String currentQuery = String.format(
				"select * from users where id_account = %s and password = md5(%s)",
				User.accountId,
				User.password
		);
		
		try {
			ResultSet result = Main.executeQuerys(currentQuery);
			User.balance = result.getFloat("balance");
			System.out.println("Checado!");
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	public void withdraw(ActionEvent event) throws IOException {
		Float valueInput = Float.parseFloat(balanceValue.getText());
		
		if (valueInput == 0 || (valueInput > User.balance)) {
			System.out.println("Adicione um valor.");
			return;
		}

		if (checkAndUpdateBalance()) {
			
			valueInput = User.balance - valueInput;

//			String currentQuery = String.format(
//					"update users set balance = %f where id_account = %s and password = md5(%s)",
//					valueInput,
//					User.accountId,
//					User.password
//			);
			
			String currentQuery = "update users set balance = " + valueInput + " where id_account = " + User.accountId + " and password = md5(" + User.password + ")";
			
			int result = Main.executeUpdates(currentQuery);
			if (result != 0 ) {
				System.out.println(User.balance);
				User.balance = valueInput;
				System.out.println(User.balance);
				System.out.println("Atualizado");
			}
			switchToHomeScreen(event);
			
		} else {
			System.out.println("Não foi possível checar o valor em sua conta.");
		}
		
	}
	
	public void deposit(ActionEvent event) throws IOException {
		Float valueInput = Float.parseFloat(balanceValue.getText());
		System.out.println(valueInput);
		
		if (valueInput <= 0) {
			System.out.println("Adicione um valor.");
			return;
		}

		if (checkAndUpdateBalance()) {
			
			valueInput = valueInput + User.balance;
			
			System.out.println(valueInput);
					
//			String currentQuery = String.format(
//					"update users set balance = %d where id_account = %s and password = md5(%s)",
//					valueInput,
//					User.accountId,
//					User.password
//			);
			
			String currentQuery = "update users set balance = " + valueInput + " where id_account = " + User.accountId + " and password = md5(" + User.password + ")";
			
			int result = Main.executeUpdates(currentQuery);
			if (result != 0 ) {
				System.out.println(User.balance);
				User.balance = valueInput;
				System.out.println(User.balance);
				System.out.println("Atualizado");
			}
			switchToHomeScreen(event);
			
		} else {
			System.out.println("Não foi possível checar o valor em sua conta.");
		}
		
	}
	
}
