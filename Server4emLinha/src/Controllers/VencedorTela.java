package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class VencedorTela {
    @FXML
    private Label LabelVencedor;

    @FXML
    Circle CorVencedor;

    public void receberDados(String nomeVencedor, String corVencedor) {
        LabelVencedor.setText("0 " + nomeVencedor);
        CorVencedor.setFill(Paint.valueOf(corVencedor));

    }

    public void Sair(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
