package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CorTela {
    private String corSelecionada;

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
        corSelecionada = "#FFD800";
    }

    public void EscolherVermelho(ActionEvent actionEvent) {
        corSelecionada = "#FF0000";
    }
}