package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeScreenController {
	
	@FXML
	private TextField textAccount;

	@FXML
	private TextField textPassword;
	
//	@FXML
//	private TextField currentBalanceValue = AddValue();
	
	
//	private TextField AddValue() {
//		currentBalanceValue.setText(Float.toString(User.balance));
//		return currentBalanceValue;
//	}
	
	public void switchToLoginScreen(ActionEvent value) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		Main.swapScenes(Main.root);
	}
	
	public void switchToWithdrawScreen(ActionEvent value) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("WithdrawScreen.fxml"));
		Main.swapScenes(Main.root);
	}
	
	public void switchToDepositScreen(ActionEvent value) throws IOException {
		Main.root = FXMLLoader.load(getClass().getResource("DepositScreen.fxml"));
		Main.swapScenes(Main.root);
	}

}
