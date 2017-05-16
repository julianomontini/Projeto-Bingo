package com.cliente;
	
import com.telas.TelaLogin;

import javafx.application.Application;
import javafx.stage.Stage;


public class Cliente extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(TelaLogin.getTela(primaryStage));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
