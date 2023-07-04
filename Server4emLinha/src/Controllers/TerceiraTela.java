package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TerceiraTela {
    @FXML
    private TextField TxtIP;
    @FXML
    private TextField TxtNome;
    @FXML
    private Label TextoLabel;

    private String corSelecionada;

    public void initialize() {
        // Buscar e definir o endereço IPv4 no TextField TXTIp
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipv4 = localHost.getHostAddress();
            TxtIP.setText(ipv4);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/JogoTela.fxml"));
            Parent root = loader.load();


            JogoTela controller = loader.getController();
            controller.receberDadosJogo(TxtNome.getText(), TxtIP.getText(),corSelecionada); // Passar o nome e o endereço IP para a JogoTela
            Stage stage = new Stage();
            stage.setTitle("Server Tela");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela atual da terceira tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receberCorSelecionada(String cor) {
        corSelecionada = cor;
        TextoLabel.setTextFill(Color.web(corSelecionada));
    }
}
