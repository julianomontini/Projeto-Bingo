package com.telas;
	
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Tela Inicial
 * @author Juliano
 *
 */
public class ProgramaCliente extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(new TelaLogin(primaryStage).getCena());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
