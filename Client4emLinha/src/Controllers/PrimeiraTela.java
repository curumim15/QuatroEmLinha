package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimeiraTela {
    public void ProximaPagina(ActionEvent actionEvent) {
        try {
            // Carregar o arquivo FXML da Segunda Tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SegundaTela.fxml"));
            Parent root = loader.load();

            // Criar um novo Stage para exibir a Segunda Tela
            Stage stage = new Stage();
            stage.setTitle("Cliente Tela");// Definir o título da janela da Segunda Tela
            stage.setScene(new Scene(root, 800, 600)); // Definir a cena com o conteúdo da Segunda Tela
            stage.show();

            // Fechar a janela atual da primeira tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Sair(ActionEvent actionEvent) {
        // Fechar a janela atual (Stage)
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        Platform.exit();// Fechar a aplicação completamente
    }
}
