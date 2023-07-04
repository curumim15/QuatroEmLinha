package Controllers;


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
        LabelVencedor.setText("0 " + nomeVencedor + " Venceu" );
        CorVencedor.setFill(Paint.valueOf(corVencedor));

    }

    public void Sair(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
