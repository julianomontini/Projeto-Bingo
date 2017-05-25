package com.telas;

import java.net.Socket;
import java.util.List;

import com.common.ConexaoClienteServidor;
import com.common.Receber;
import com.database.Conexao;
import com.database.DAOS.UsuariosDAO;
import com.database.DBOS.UsuarioBO;
import com.jogo.objetosConexao.Cartela;
import com.jogo.objetosConexao.Mensagem;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaLogin implements Receber{
	
	private Scene cena;
	private Label mensagens;
	private ConexaoClienteServidor conexao;
	private Stage primaryStage;
	public TelaLogin(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.cena = setup(primaryStage);
	}
	
	

	public Scene getCena() {
		return cena;
	}

	private Scene setup(Stage primaryStage) {
		
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
						TelaLogin.this.mensagens.setText("Esse Usuario Nao Existe");
					else{
						TelaLogin.this.conexao = new ConexaoClienteServidor(new Socket("localhost", 4200), TelaLogin.this);
					}

				} catch (Exception e) {
					TelaLogin.this.mensagens.setText("Não foi possivel iniciar uma conexão com o servidor");
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
		this.mensagens = mensagens;

		//retorna cena com todos os elementos criados
		Scene c = new Scene(root, 400, 250);
		c.getStylesheets().add("style.css");
		
		
		return c;
	}



	@Override
	public void receber(Object o) {
		if(o instanceof Mensagem){
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					TelaLogin.this.mensagens.setText("Mensagem: " + ((Mensagem)o).getTexto());
				}
				
			});
		}
		
		if(o instanceof Cartela){
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					TelaJogo telaJogo = new TelaJogo(TelaLogin.this.primaryStage,(Cartela)o, TelaLogin.this.conexao);
					TelaLogin.this.conexao.setReceber(telaJogo);
					TelaLogin.this.primaryStage.setScene(telaJogo.getTela());
					TelaLogin.this.primaryStage.show();
				}
			});
		}
		
	}

}
