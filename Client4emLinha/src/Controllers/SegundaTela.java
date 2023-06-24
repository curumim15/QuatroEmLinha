package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SegundaTela {
    private String corSelecionada;
    @FXML
    private Label TextoLabel;
    @FXML
    private Button BtnLigar;
    public void initialize(){
        BtnLigar.setVisible(false);

    }
    public void EscolherCor(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/CorTela.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Seleção de Cor CLient");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da segunda tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ProximaPagina(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TerceiraTela.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cliente Tela");
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
            stage.setTitle("Cliente Tela");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da segunda tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnJogarVisibel(){
        if (corSelecionada== null){
            BtnLigar.setVisible(false);
        }else {
            BtnLigar.setVisible(true);
        }
    }

    public void receberCorSelecionada(String cor) {
        corSelecionada = cor;
        TextoLabel.setTextFill(Color.web(corSelecionada));
    }
}
