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
import javafx.stage.Stage;

/**
 * Tela de cadastro
 * @author Juliano
 *
 */
public class TelaCadastro {

	private TelaCadastro(){}
	
	public static Scene getTela(Stage primaryStage){
		
		GridPane root = new GridPane();
		
		Label textoNome = new Label();
		Label textoEmail = new Label();
		Label textoSenha = new Label();
		Label textoConfSenha = new Label();
		TextField inputNome = new TextField();
		TextField inputEmail = new TextField();
		TextField inputSenha = new TextField();
		TextField inputConfirmacao = new TextField();
		Button botaoConfirmar = new Button();
		Button botaoCancelar = new Button();
		
		textoNome.setText("Nome: ");
		textoEmail.setText("Email: ");
		textoSenha.setText("Senha: ");
		textoConfSenha.setText("Confirmar: ");
		
		botaoCancelar.setText("Cancelar");
		botaoConfirmar.setText("Confirmar");
		
		botaoCancelar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				primaryStage.setScene(new TelaLogin(primaryStage).getCena());
				primaryStage.show();	
			}
		});
		
		botaoConfirmar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				try {
					
					UsuariosDAO mgrUsuario = new UsuariosDAO(Conexao.getConnection());
					
					if(inputEmail.getText().length() == 0)
						throw new Exception("O email nao pode estar vazio");
					
					if(textoSenha.getText().length() == 0)
						throw new Exception("A senha nao pode estar vazia");
					
					if(!textoSenha.getText().equals(textoSenha.getText()))
						throw new Exception("As senhas digitadas precisam ser iguais");
					
					if(textoNome.getText().length() == 0)
						throw new Exception("O nome nao pode estar vazio");
					
					List<UsuarioBO> usuarios = mgrUsuario.select(new String[]{"email"}, new String[]{inputEmail.getText()});
					
					if(!usuarios.isEmpty())
						throw new Exception("O email já esta cadastrado");
					
					UsuarioBO usuario = new UsuarioBO(null, inputNome.getText(), inputEmail.getText(), inputSenha.getText());
					
					mgrUsuario.create(usuario);
					
					primaryStage.setScene(new TelaLogin(primaryStage).getCena());
					primaryStage.show();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		root.add(textoNome, 0, 0);
		root.add(inputNome, 1, 0);
		
		root.add(textoEmail, 0, 1);
		root.add(inputEmail, 1, 1);
		
		root.add(textoSenha, 0, 2);
		root.add(inputSenha, 1, 2);
		
		root.add(textoConfSenha, 0, 3);
		root.add(inputConfirmacao, 1, 3);
		
		root.add(botaoConfirmar, 0, 4);
		root.add(botaoCancelar, 1, 4);
		
		return new Scene(root, 250, 200);
		
	}
	
}
