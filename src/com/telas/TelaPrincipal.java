package com.telas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TelaPrincipal {

	public static Scene getTelaPrincipal(Stage primaryStage){
		StackPane root = new StackPane();
		Button botao = new Button();
		botao.setText("Botao2");
		root.getChildren().add(botao);
		return new Scene(root, 200, 200);
	}
	
}
