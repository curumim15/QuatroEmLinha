package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class TerceiraTela {

    private String corSelecionada;
    public void Voltar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SegundaTela.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Segunda Tela");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da tela de seleção de cor
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TelaJogo(ActionEvent actionEvent) {

    }
    public void receberCorSelecionada(String cor) {
        corSelecionada = cor;
    }
}
