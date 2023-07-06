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
    // Referências do FXML
    @FXML
    private GridPane gridPane;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label nome2Label,labelEspera,LabelColuna;
    @FXML
    private Circle ColorP1,ColorP2;
    @FXML
    private Button Coluna1,Coluna2,Coluna3,Coluna4,Coluna5,Coluna6,Coluna7;
    //Variaveis para dois jogadores
    private Jogador player,player2;
    @FXML
    private Rectangle rec1,rec2;

    //Variavel para verificar de tem vencedor
    private boolean win=false;

    //Variavel Tabuleiro
    private Tabuleiro board;

    //Varialvel do Jogador que esta a jogar
    private Jogador currentPlayer;

    //Variavel Servidor
    private Servidor servidor;

    public void initialize() {
        // Cria e inicia o servidor
        servidor = new Servidor(this);
        servidor.iniciarServidor();

        board = new Tabuleiro(6, 7); // Cria um novo tabuleiro com 6 linhas e 7 colunas
        // Adiciona os eventos de clique aos botões
        Coluna1.setOnAction(event -> handleButtonClick(0));
        Coluna2.setOnAction(event -> handleButtonClick(1));
        Coluna3.setOnAction(event -> handleButtonClick(2));
        Coluna4.setOnAction(event -> handleButtonClick(3));
        Coluna5.setOnAction(event -> handleButtonClick(4));
        Coluna6.setOnAction(event -> handleButtonClick(5));
        Coluna7.setOnAction(event -> handleButtonClick(6));
        // Defina a largura da borda do retângulo rec2 como 5
        rec1.setStrokeWidth(5);


    }

    //Metodo de ao clicar no botão da coluna Desejada
    public void handleButtonClick(int column) {
        // Chama o método dropPeca do tabuleiro para colocar uma peça na coluna selecionada pelo jogador atual
        int row = board.dropPeca(column, currentPlayer);
        // Verifica se a queda da peça foi bem-sucedida
        if (row != -1) {
            Circle circle = getCircle(row, column);// Obtém o círculo correspondente à posição onde a peça foi colocada
            animatePieceDrop(circle);// Anima a queda da peça
            circle.setFill(Color.web(currentPlayer.getColor()));// Define a cor do círculo como a cor do jogador atual
            circle.setVisible(true);// Torna o círculo visível

            // Envia a mensagem com a coluna selecionada para o servidor, apenas se for a vez do jogador 1
            if(currentPlayer==player){
                servidor.enviarMensagem(Integer.toString(column));
            }
            checkForWinner(row, column);// Verifica se houve uma vitória após a jogada

            switchPlayers();// Alterna para o próximo jogador
            // Define a visibilidade dos botões das colunas com base no jogador atual
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

    //Metodo para animação da peça a cair
    private void animatePieceDrop(Circle circle) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), circle);// Cria uma transição de animação de duração de 1 segundo para o círculo
        tt.setToY(0); // Define a posição final do círculo no eixo Y, determinando a distância vertical que a peça vai cair
        tt.play();// Inicia a animação
    }

    //Metodo para obter o circulo na posição desejada
    private Circle getCircle(int row, int column) {
        // Filtra os nós dentro do GridPane para encontrar o círculo na posição desejada (linha e coluna)
        Node node = gridPane.getChildren().stream()
                .filter(child -> GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == column)
                .findFirst()
                .orElse(null);

        if (node instanceof Circle) {// Retorna o círculo encontrado
            return (Circle) node;
        } else {
            return null;// Retorna null caso não encontre um círculo na posição desejada
        }
    }

    //Metodo para verificar Vencedor
    private void checkForWinner(int row, int column) {
        // Verifica se há um vencedor após cada jogada
        if (board.checkWin(row, column, currentPlayer)) {
            Platform.runLater(() -> {
                openVencedorTela(currentPlayer.getName(), currentPlayer.getColor());
            });
            // Define a variável de controle de vitória como verdadeira
            win=true;
            // Desabilita todos os botões após o jogo terminar
            disableAllButtons();
        } else if (board.isFull()) {//Empate
            win=true; // Define a variável de controle de vitória como verdadeira
            disableAllButtons(); // Desabilita todos os botões após o jogo terminar
            // Abre a tela de vencedor com o nome "Ninguém" e a cor branca (#FFFFFF)
            Platform.runLater(() -> {
                openVencedorTela("Ninguem","#FFFFFF");
            });
        }
    }

    //Metodo para Trocar o jogador atual
    private void switchPlayers() {
        // Alterna entre os jogadores atualmente em jogo
        if(win==false){
            // Verifica se o jogador atual é o jogador 1 e, se sim, define o jogador atual como jogador 2, caso contrário, define o jogador atual como jogador 1
            currentPlayer = (currentPlayer == player) ? player2 : player;
        }
        // Atualiza a aparência visual para indicar o jogador atual
        if(currentPlayer==player){
            rec1.setStrokeWidth(5);// Destaca o retângulo do jogador 1
            rec2.setStrokeWidth(0);// Remove o destaque do retângulo do jogador 2
            LabelColuna.setVisible(true);// Torna o Label de seleção de coluna visível
        }else {
            rec2.setStrokeWidth(5);// Destaca o retângulo do jogador 2
            rec1.setStrokeWidth(0);// Remove o destaque do retângulo do jogador 1
            LabelColuna.setVisible(false);// Torna o Label de seleção de coluna invisível
        }
    }

    //Metodo para Desativar os botões
    private void disableAllButtons() {
        Coluna1.setVisible(false);
        Coluna2.setVisible(false);
        Coluna3.setVisible(false);
        Coluna4.setVisible(false);
        Coluna5.setVisible(false);
        Coluna6.setVisible(false);
        Coluna7.setVisible(false);
    }

    //Metodo que recebe os dados vindo da TerceiraTela como nome do Jogador IP e Cor Selecionada
    public void receberDadosJogo(String nome, String ip,String cor) {
        // Cria os objetos Jogador para o jogador atual (player) e o jogador adversário (player2)
        player = new Jogador();
        player2 = new Jogador();

        // Define o nome e a cor do jogador atual (player)
        player.setName(nome);
        player.setColor(cor);

        // Define a cor do jogador adversário (player2) com base na cor do jogador atual
        if(player.getColor()== "#f9d967"){
            player2.setColor("#e773ff");
        }else {
            player2.setColor("#f9d967");
        }

        // Define os nomes dos jogadores nos respetivos Labels na interface gráfica
        nomeLabel.setText(player.getName());
        nome2Label.setText(player2.getName());

        // Define as cores dos círculos dos jogadores na interface gráfica
        ColorP1.setFill(Color.web(player.getColor()));
        ColorP2.setFill(Color.web(player2.getColor()));

        // Define o jogador atual como o jogador atual (player)
        currentPlayer = player;

        // Realiza uma animação nos botões de seleção de cor
        animaçãoBtnCor();
    }

    // Realiza a animação nos botões de seleção de coluna conforme a cor do jogador atual
    public void animaçãoBtnCor(){
        // Cria um objeto CornerRadii com raio de 100 para arredondar o background do botão
        CornerRadii cornerRadii = new CornerRadii(100);
        // Cria um objeto BackgroundFill com a cor do jogador atual e o CornerRadii definido
        BackgroundFill BackgroundFill = new BackgroundFill(Color.web(player.getColor()), cornerRadii, null);
        // Cria um objeto Background com o BackgroundFill definido para o jogador atual
        Background playerBackground = new Background(BackgroundFill);
        // Cria um objeto BackgroundFill transparente com o CornerRadii definido
        BackgroundFill transparentFill = new BackgroundFill(Color.TRANSPARENT, cornerRadii, null);
        // Cria um objeto Background transparente com o BackgroundFill definido
        Background transparentBackground = new Background(transparentFill);

        // Define o background dos botões para o background do jogador quando o mouse entra no botão
        // Define o background dos botões  para transparente quando o mouse sai do botão
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

    //Metodo para defenir o nome do Segundo jogador
    public void segundoPLayer(String nome){
        // Atualiza o nome do segundo jogador com o nome recebido
        player2.setName(nome);
        // Atualiza o texto do nome2Label na interface gráfica com o nome do segundo jogador
        Platform.runLater(() -> nome2Label.setText(player2.getName()));
    }

    //Metodo para enviar o nome do primeiro jogador para o servidor
    public void enviarNomeP(){
        servidor.enviarMensagem(player.getName());
    }

    //Metodo para desativar a label espera quando o cliente entrar
    public void esperaClient(){
        labelEspera.setVisible(false);
    }

    //Metodo para abrir  a VencedorTela com o nome e a cor do respetivo Vencedor
    private void openVencedorTela(String nomeVencedor, String corVencedor) {
        // Abre a tela de vencedor
        try {
            // Carrega o arquivo FXML da VencedorTela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();
            // Obtém o controlador da VencedorTela
            VencedorTela controller = loader.getController();
            // Passa os dados do vencedor para o controlador da VencedorTela
            controller.receberDados(nomeVencedor, corVencedor);

            // Cria e exibe o stage da VencedorTela
            Stage stage = new Stage();
            stage.setTitle("Tela do Vencedor");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual da tela de jogo
            Stage currentStage = (Stage)  labelEspera.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para o jogador desistir
    public void Desistir(ActionEvent actionEvent) {
        try {
            // Carrega o arquivo FXML da VencedorTela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();
            // Obtém o controlador da VencedorTela
            VencedorTela controller = loader.getController();
            // Passa os dados do segundo jogador como vencedor para o controlador da VencedorTela
            controller.receberDados(player2.getName(), player2.getColor());

            // Envia uma mensagem para o cliente informando que o jogador desistiu
            servidor.enviarMensagem("Desistiu");
            // Cria e exibe o stage da VencedorTela
            Stage stage = new Stage();
            stage.setTitle("Tela do Vencedor");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual da tela de jogo
            Stage currentStage = (Stage) labelEspera.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para tratar a desistência do segundo jogador
    public void SegundoPlayerDesistir(){
        try {
            // Carrega o arquivo FXML da VencedorTela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VencedorTela.fxml"));
            Parent root = loader.load();
            // Obtém o controlador da VencedorTela
            VencedorTela controller = loader.getController();
            // Passa os dados do primeiro jogador como vencedor para o controlador da tela de Vencedor
            controller.receberDados(player.getName(), player.getColor());

            // Executa no thread da interface gráfica
            Platform.runLater(() ->{
                // Cria e exibe o stage da VencedorTela
                Stage stage = new Stage();
                stage.setTitle("Tela do Vencedor");
                stage.setScene(new Scene(root));
                stage.show();
                // Fecha a janela atual da tela de jogo
                Stage currentStage = (Stage) labelEspera.getScene().getWindow();
                currentStage.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
