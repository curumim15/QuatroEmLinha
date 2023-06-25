package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TerceiraTela {
    @FXML
    private TextField TxtIP;
    @FXML
    private TextField TxtNome;
    @FXML
    private Label TextoLabel;

    public void TelaJogo(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/JogoTela.fxml"));
            Parent root = loader.load();


            JogoTela controller = loader.getController();
            controller.receberDadosJogo(TxtNome.getText(), TxtIP.getText(),corSelecionada);
            Stage stage = new Stage();
            stage.setTitle("Cliente Tela");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da terceira tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
