package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SegundaTela {
    public void EscolherCor(ActionEvent actionEvent) {
    }

    public void ProximaPagina(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TerceiraTela.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("Server");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da segunda tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Voltar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/PrimeiraTela.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Server Tela");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da segunda tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
