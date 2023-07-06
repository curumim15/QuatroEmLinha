package Controllers;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class VencedorTela {
    @FXML
    private Label LabelVencedor;

    @FXML
    Circle CorVencedor;

    public void receberDados(String nomeVencedor, String corVencedor) {
        LabelVencedor.setText(nomeVencedor);// Definir o texto da label com o nome do vencedor proveniente do JogoTela
        CorVencedor.setFill(Paint.valueOf(corVencedor)); // Definir a cor de preenchimento do círculo com base na cor do vencedor

    }

    public void Sair(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();// Fechar a janela atual da VencedorTela
        Platform.exit();// Fechar a aplicação completamente
    }
}
