package Controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class PrimeiraTela {
    public void ProximaPagina(ActionEvent actionEvent) {
    }

    public void Sair(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
}
