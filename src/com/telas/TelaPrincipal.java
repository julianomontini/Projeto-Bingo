package com.telas;

import java.util.ArrayList;
import java.util.List;

import com.common.ConexaoClienteServidor;
import com.jogo.Bingo;
import com.jogo.objetosConexao.Cartela;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class TelaPrincipal{
	
	private List<Integer> numerosEscolhidos;
	private Scene tela;
	private Stage primaryStage;
	private ConexaoClienteServidor conexao;
	private Label mensagens;
	public TelaPrincipal(Stage primaryStage, Cartela c, ConexaoClienteServidor conexao){
		this.numerosEscolhidos = new ArrayList<Integer>();
		this.conexao = conexao;
		this.tela = getTelaPrincipal(c);
		primaryStage.setScene(this.tela);
		this.primaryStage = primaryStage;
		primaryStage.show();
	}

	public Scene getTelaPrincipal(Cartela c){
		
		BorderPane root = new BorderPane();
		GridPane tabuleiro = new GridPane();
		
		
		char[] bingo = "BINGO".toCharArray();
		for(int i = 0; i < 5; i++){
			Button b = new Button();
			b.setText(String.valueOf(bingo[i]));
			b.setDisable(true);
			b.setMinWidth(100);
			b.setMinHeight(100);
			tabuleiro.add(b, i, 0);
		}
		
		Integer[][] cartela = convertToMatriz(Bingo.geraCartela().getValores());
		
		for(int i = 0 ; i < 5; i ++){
			for(int j = 0; j < 5; j++){
				Button b = new Button();
				b.setMinWidth(100);
				b.setMinHeight(100);
				
				if(cartela[i][j] == null){
					b.setText("BINGO");
					b.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							
						}
					});
				}else{
					int valor = cartela[i][j];
					b.setId(String.valueOf(valor));
					b.setText(String.valueOf(valor));
					b.setOnMouseClicked(new EventHandler<Event>() {
						Boolean ativo = false;
						@Override
						public void handle(Event event) {
							Integer id = Integer.valueOf(b.getId());
							if(TelaPrincipal.this.numerosEscolhidos.contains(id)){
								b.setStyle("-fx-background-color: #E2E2E2");
								TelaPrincipal.this.numerosEscolhidos.remove(id);
							}else{
								b.setStyle("-fx-background-color: #7F7F7F");
								TelaPrincipal.this.numerosEscolhidos.add(id);
							}
						}
					});
				}
				tabuleiro.add(b, i, j+1);
			}
		}
		
		
		root.setCenter(tabuleiro);
		
		Label mensagens = new Label();
		mensagens.setText("Alguma mensagem aqui");
		mensagens.setStyle("-fx-font-size: 25px");
		this.mensagens = mensagens;
		tabuleiro.addRow(6, mensagens);
		root.setBottom(mensagens);
		root.getBottom().setTranslateX(125);
		root.getBottom().setTranslateY(-25);
		
		return new Scene(root, 700, 700);
	}
	
	private static Integer[][] convertToMatriz(Integer[] array){
		Integer[][] retorno = new Integer[5][5];
		int contador = 0;
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(i == 2 && j == 2){
					retorno[i][j] = null;
				}else{
					retorno[i][j] = array[contador];
					contador++;
				}
			}
		}
		
		return retorno;
	}

	public List<Integer> getNumerosEscolhidos() {
		return numerosEscolhidos;
	}

	public void setNumerosEscolhidos(List<Integer> numerosEscolhidos) {
		this.numerosEscolhidos = numerosEscolhidos;
	}

	public Scene getTela() {
		return tela;
	}

	public void setTela(Scene tela) {
		this.tela = tela;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void escreverMensagem(String texto){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				TelaPrincipal.this.mensagens.setText(texto);
				
			}
			
		});
	}
	
}
