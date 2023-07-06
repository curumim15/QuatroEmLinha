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
       corSelecionada="#f9d967";

    }

    public void Voltar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SegundaTela.fxml"));
            Parent root = loader.load();

            SegundaTela controller = loader.getController();
            controller.receberCorSelecionada(corSelecionada);

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

    public void EscolherAmarelo(ActionEvent actionEvent) {
        corSelecionada = "#f9d967";
        BtnAmarelo.setStyle("-fx-background-radius:100;-fx-background-color: #f9d967;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:5");
        BtnVermelho.setStyle("-fx-background-radius:100;-fx-background-color: #e773ff;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:0");
    }

    public void EscolherVermelho(ActionEvent actionEvent) {
        corSelecionada = "#e773ff";
        BtnVermelho.setStyle("-fx-background-radius:100;-fx-background-color: #e773ff;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:5");
        BtnAmarelo.setStyle("-fx-background-radius:100;-fx-background-color: #f9d967;-fx-border-color:White;-fx-border-radius:100;-fx-border-width:0");
    }
}