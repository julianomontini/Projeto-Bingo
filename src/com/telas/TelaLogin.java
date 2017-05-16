package com.telas;

import java.util.List;

import com.database.Conexao;
import com.database.DAOS.UsuariosDAO;
import com.database.DBOS.UsuarioBO;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaLogin{
	
	//Criado construtor privado, pois todos os metodos dessa classe serao estaticos, portanto, 
	//nao faz sentido ter uma instancia dela
	private TelaLogin(){}

	public static Scene getTela(Stage primaryStage) {
		
		//Cria tela do tipo Grid
		GridPane root = new GridPane();

		//Inicializa os campos da tela
		Text textoLogin = new Text();
		TextField login = new TextField();
		TextField senha = new TextField();
		Button botaoLogin = new Button();
		Button botaoCadastro = new Button();
		Button botaoCancelar = new Button();
		Label mensagens = new Label();
		Text textoSenha = new Text();

		//Coloca texto nos Labels
		textoLogin.setText("Email: ");
		textoSenha.setText("Senha: ");

		//Coloca texto nos botoes
		botaoLogin.setText("Login");
		botaoCadastro.setText("Cadastrar");
		botaoCancelar.setText("Cancelar");

		botaoCancelar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				primaryStage.close();
				
			}
		});
		
		//Define comportamento quando o botao de login for clicado
		botaoLogin.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {

				try {
					//usa o DAO de usuarios para recuperar os usuarios com o email e senha informados no input
					List<UsuarioBO> usuarios = new UsuariosDAO(Conexao.getConnection()).select(new String[]{"email", "senha"}, new String[]{login.getText(), senha.getText()});
					
					//Se o tamanho da lista for 0 significa que a query não retornou resultados
					if (usuarios.size() == 0)
						throw new Exception("Esse usuario nao existe");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		botaoCadastro.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				primaryStage.setScene(TelaCadastro.getTela(primaryStage));
				primaryStage.show();
				
			}
		});

		//Adiciona os elementos a tela
		root.add(textoLogin, 0, 0);
		root.add(login, 1, 0);
		root.add(textoSenha, 0, 1);
		root.add(senha, 1, 1);
		root.add(botaoLogin, 0, 2);
		root.add(botaoCancelar, 1, 2);
		root.add(botaoCadastro, 0, 3);
		root.add(mensagens, 1, 4);

		//retorna cena com todos os elementos criados
		return new Scene(root, 400, 250);
	}

}
