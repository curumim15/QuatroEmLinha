package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CorTela {
    private String corSelecionada;

    @FXML
    Button BtnAmarelo,BtnVermelho;

    public void initialize(){
        corSelecionada="#f9d967";// Define a cor inicial como amarelo
    }

    public void Voltar(ActionEvent actionEvent) {
        try {
            // Carregar o arquivo FXML da Segunda Tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SegundaTela.fxml"));
            Parent root = loader.load();

            // Obter o controlador da Segunda Tela
            SegundaTela controller = loader.getController();
            //Envia a corSelecionada para a Segunda Tela
            controller.receberCorSelecionada(corSelecionada);

            // Criar um novo Stage para exibir a Segunda Tela
            Stage stage = new Stage();
            stage.setTitle("Server");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da tela de seleção de cor
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Método chamado quando o botão "Amarelo" é clicado para escolher a Cor Amarelo
    public void EscolherAmarelo(ActionEvent actionEvent) {
        //Define a corSelecionada como Amarelo
        corSelecionada = "#f9d967";
        //Coloca a Borda do botão Amarelo maior
        BtnAmarelo.setStyle("-fx-background-radius:100;-fx-background-color: #f9d967;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:5");
        //Coloca a Borda do botão Vermelho menor
        BtnVermelho.setStyle("-fx-background-radius:100;-fx-background-color: #e773ff;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:0");
    }

    // Método chamado quando o botão "Vermelho" é clicado para escolher a Cor Vermelha
    public void EscolherVermelho(ActionEvent actionEvent) {
        //Define a corSelecionada como Vermelha
        corSelecionada = "#e773ff";
        //Coloca a Borda do botão Vermelho maior
        BtnVermelho.setStyle("-fx-background-radius:100;-fx-background-color: #e773ff;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:5");
        //Coloca a Borda do botão Amarelo menor
        BtnAmarelo.setStyle("-fx-background-radius:100;-fx-background-color: #f9d967;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:0");
    }
}