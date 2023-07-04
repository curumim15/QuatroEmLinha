package Controllers;


import Jogo.Jogador;
import Jogo.Tabuleiro;
import Server.Servidor;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class JogoTela {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label nomeLabel; // Referência ao Label para exibir o nome
    @FXML
    private Label nome2Label,labelEspera,labelColuna;
    @FXML
    private Circle ColorP1,ColorP2;
    @FXML
    private Button Coluna1,Coluna2,Coluna3,Coluna4,Coluna5,Coluna6,Coluna7;
    private Jogador player,player2; // Objeto Player
    @FXML
    private Rectangle rec1,rec2;
    private boolean win=false;

    private Tabuleiro board;

    private Jogador currentPlayer;

    private Servidor servidor;

    public void initialize() {
        servidor = new Servidor(this);
        servidor.iniciarServidor();

        // Inicialize o jogo aqui
        board = new Tabuleiro(6, 7); // Exemplo de tabuleiro 6x7
        // Adicione os eventos de clique aos botões
        Coluna1.setOnAction(event -> handleButtonClick(0));
        Coluna2.setOnAction(event -> handleButtonClick(1));
        Coluna3.setOnAction(event -> handleButtonClick(2));
        Coluna4.setOnAction(event -> handleButtonClick(3));
        Coluna5.setOnAction(event -> handleButtonClick(4));
        Coluna6.setOnAction(event -> handleButtonClick(5));
        Coluna7.setOnAction(event -> handleButtonClick(6));
        rec1.setStrokeWidth(5);


    }

    public void handleButtonClick(int column) {
        int row = board.dropPeca(column, currentPlayer);
        if (row != -1) {
            Circle circle = getCircle(row, column);
            animatePieceDrop(circle);
            circle.setFill(Color.web(currentPlayer.getColor()));
            circle.setVisible(true);

            // Definir a cor desejada
            if(currentPlayer==player){
                servidor.enviarMensagem(Integer.toString(column));
            }
            checkForWinner(row, column);
            switchPlayers();
            if(currentPlayer==player2){
                Coluna1.setVisible(false);
                Coluna2.setVisible(false);
                Coluna3.setVisible(false);
                Coluna4.setVisible(false);
                Coluna5.setVisible(false);
                Coluna6.setVisible(false);
                Coluna7.setVisible(false);
                //labelColuna.setVisible(false);
            }else {
                Coluna1.setVisible(true);
                Coluna2.setVisible(true);
                Coluna3.setVisible(true);
                Coluna4.setVisible(true);
                Coluna5.setVisible(true);
                Coluna6.setVisible(true);
                Coluna7.setVisible(true);
                //labelColuna.setVisible(true);
            }

        }
    }

    private void animatePieceDrop(Circle circle) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), circle);
        tt.setToY(0); // Define a distância vertical que a ficha vai cair
        tt.play();
    }

    private Circle getCircle(int row, int column) {
        Node node = gridPane.getChildren().stream()
                .filter(child -> GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == column)
                .findFirst()
                .orElse(null);

        if (node instanceof Circle) {
            return (Circle) node;
        } else {
            return null;
        }
    }

    private void checkForWinner(int row, int column) {
        if (board.checkWin(row, column, currentPlayer)) {
            Platform.runLater(() -> {
                openVencedorTela(currentPlayer.getName(), currentPlayer.getColor());
            });
            win=true;
            disableAllButtons();
        } else if (board.isFull()) {
            win=true;
            disableAllButtons();
        }
    }

    private void switchPlayers() {
        if(win==false){
            currentPlayer = (currentPlayer == player) ? player2 : player;
        }
        if(currentPlayer==player){
            rec1.setStrokeWidth(5);
            rec2.setStrokeWidth(0);
        }else {
            rec2.setStrokeWidth(5);
            rec1.setStrokeWidth(0);
        }
    }

    private void disableAllButtons() {
        Coluna1.setVisible(false);
        Coluna2.setVisible(false);
        Coluna3.setVisible(false);
        Coluna4.setVisible(false);
        Coluna5.setVisible(false);
        Coluna6.setVisible(false);
        Coluna7.setVisible(false);
        // Desative os demais botões
    }

    public void receberDadosJogo(String nome, String ip,String cor) {
        player = new Jogador();
        player2 = new Jogador();

        player.setName(nome);
        player.setColor(cor);


        if(player.getColor()== "#FFD800"){
            player2.setColor("#FF0000");
        }else {
            player2.setColor("#FFD800");
        }

        nomeLabel.setText(player.getName());
        nome2Label.setText(player2.getName());

        ColorP1.setFill(Color.web(player.getColor()));
        ColorP2.setFill(Color.web(player2.getColor()));

        currentPlayer = player;



        animaçãoBtnCor();
    }

    public void animaçãoBtnCor(){
        CornerRadii cornerRadii = new CornerRadii(100);
        BackgroundFill BackgroundFill = new BackgroundFill(Color.web(player.getColor()), cornerRadii, null);
        Background playerBackground = new Background(BackgroundFill);
        BackgroundFill transparentFill = new BackgroundFill(Color.TRANSPARENT, cornerRadii, null);
        Background transparentBackground = new Background(transparentFill);

        Coluna1.setOnMouseEntered(event -> Coluna1.setBackground(playerBackground));
        Coluna1.setOnMouseExited(event -> Coluna1.setBackground(transparentBackground));


        Coluna2.setOnMouseEntered(event -> Coluna2.setBackground(playerBackground));
        Coluna2.setOnMouseExited(event -> Coluna2.setBackground(transparentBackground));

        Coluna3.setOnMouseEntered(event -> Coluna3.setBackground(playerBackground));
        Coluna3.setOnMouseExited(event -> Coluna3.setBackground(transparentBackground));

        Coluna4.setOnMouseEntered(event -> Coluna4.setBackground(playerBackground));
        Coluna4.setOnMouseExited(event -> Coluna4.setBackground(transparentBackground));

        Coluna5.setOnMouseEntered(event -> Coluna5.setBackground(playerBackground));
        Coluna5.setOnMouseExited(event -> Coluna5.setBackground(transparentBackground));

        Coluna6.setOnMouseEntered(event -> Coluna6.setBackground(playerBackground));
        Coluna6.setOnMouseExited(event -> Coluna6.setBackground(transparentBackground));

        Coluna7.setOnMouseEntered(event -> Coluna7.setBackground(playerBackground));
        Coluna7.setOnMouseExited(event -> Coluna7.setBackground(transparentBackground));

    }

    public void segundoPLayer(String nome){
        player2.setName(nome);
        Platform.runLater(() -> nome2Label.setText(player2.getName()));
    }

    public void enviarNomeP(){
        servidor.enviarMensagem(player.getName());
    }

    public void esperaClient(){
        labelEspera.setVisible(false);
    }

    private void openVencedorTela(String nomeVencedor, String corVencedor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();

            VencedorTela controller = loader.getController();
            controller.receberDados(nomeVencedor, corVencedor);

            Stage stage = new Stage();
            stage.setTitle("Tela do Vencedor");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage)  labelEspera.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Desistir(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();
            VencedorTela controller = loader.getController();
            controller.receberDados(player2.getName(), player2.getColor());

            servidor.enviarMensagem("Desistiu");
            Stage stage = new Stage();
            stage.setTitle("Tela do Vencedor");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) labelEspera.getScene().getWindow();

            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SegundoPlayerDesistir(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();

            VencedorTela controller = loader.getController();
            controller.receberDados(player.getName(), player.getColor());

            Platform.runLater(() ->{
                Stage stage = new Stage();
                stage.setTitle("Tela do Vencedor");
                stage.setScene(new Scene(root));
                stage.show();
                Stage currentStage = (Stage) labelEspera.getScene().getWindow();
                currentStage.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
