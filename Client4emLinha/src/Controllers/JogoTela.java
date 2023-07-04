package Controllers;

import Client.Client;
import Jogo.Jogador;
import Jogo.Tabuleiro;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class JogoTela {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label nomeLabel; // Referência ao Label para exibir o nome
    @FXML
    private Label ipLabel; // Referência ao Label para exibir o endereço IP
    @FXML
    private Circle ColorP1,ColorP2;
    @FXML
    private Button Coluna1,Coluna2,Coluna3,Coluna4,Coluna5,Coluna6,Coluna7;
    private Jogador player,player2; // Objeto Player
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label winnerLabel;
    private boolean win=false;

    private Tabuleiro board;

    private Jogador currentPlayer;

    private Client client;

    public void initialize() {

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
        Coluna1.setVisible(false);
        Coluna2.setVisible(false);
        Coluna3.setVisible(false);
        Coluna4.setVisible(false);
        Coluna5.setVisible(false);
        Coluna6.setVisible(false);
        Coluna7.setVisible(false);

    }

    public void handleButtonClick(int column) {
        int row = board.dropPeca(column, currentPlayer);
        if (row != -1) {

            Circle circle = getCircle(row, column);
            animatePieceDrop(circle);
            circle.setFill(Color.web(currentPlayer.getColor()));
            circle.setVisible(true);

            // Definir a cor desejada
            if (currentPlayer==player){
                client.enviarMensagem(Integer.toString(column));
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
            }else {
                Coluna1.setVisible(true);
                Coluna2.setVisible(true);
                Coluna3.setVisible(true);
                Coluna4.setVisible(true);
                Coluna5.setVisible(true);
                Coluna6.setVisible(true);
                Coluna7.setVisible(true);
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

                winnerLabel.setText(currentPlayer.getName() + " wins!");
                openVencedorTela(currentPlayer.getName(), currentPlayer.getColor());

            });

            disableAllButtons();
            win=true;
        } else if (board.isFull()) {
            Platform.runLater(() -> winnerLabel.setText("It's a draw!"));
            disableAllButtons();
            win=true;
        }
    }

    private void switchPlayers() {
        if(win==false){
            currentPlayer = (currentPlayer == player) ? player2 : player;
            Platform.runLater(() ->  currentPlayerLabel.setText(currentPlayer.getName()));
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

        //player2.setName("Segundo Player");
        // client.enviarMensagem(player.getName());
        if(player.getColor()== "#FFD800"){
            player2.setColor("#FF0000");
        }else {
            player2.setColor("#FFD800");
        }

        nomeLabel.setText(player.getName());


        ColorP1.setFill(Color.web(player.getColor()));
        ColorP2.setFill(Color.web(player2.getColor()));


        if(currentPlayer==player2){
            Coluna1.setDisable(true);
        }

        currentPlayer = player2;
        currentPlayerLabel.setText(currentPlayer.getName());

        client = new Client(this);
        client.conectarAoServidor(ip);

    }

    public void segundoPLayer(String nome){
        player2.setName(nome);
        Platform.runLater(() -> ipLabel.setText(player2.getName()));
    }

    public void enviarNomeP(){
        client.enviarMensagem(player.getName());
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

            Stage currentStage = (Stage) winnerLabel.getScene().getWindow();
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

            client.enviarMensagem("Desistiu");
            Stage stage = new Stage();
            stage.setTitle("Tela do Vencedor");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) winnerLabel.getScene().getWindow();

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
                Stage currentStage = (Stage) winnerLabel.getScene().getWindow();
                currentStage.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
