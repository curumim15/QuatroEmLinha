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

    public void Voltar(ActionEvent actionEvent) {
        try {
            // Carregar o arquivo FXML da Segunda Tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SegundaTela.fxml"));
            Parent root = loader.load();

            // Criar um novo Stage para exibir a Segunda Tela
            Stage stage = new Stage();
            stage.setTitle("Segunda Tela");// Definir o título da janela da Segunda Tela
            stage.setScene(new Scene(root, 800, 600));// Definir a cena com o conteúdo da Segunda Tela
            stage.show();

            // Fechar a janela atual da Terceira Tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TelaJogo(ActionEvent actionEvent) {
        try {
            // Carregar o arquivo FXML da JogoTela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/JogoTela.fxml"));
            Parent root = loader.load();

            // Obter o controlador da JogoTela
            JogoTela controller = loader.getController();
            controller.receberDadosJogo(TxtNome.getText(), TxtIP.getText(),corSelecionada); // Passar o nome, endereço IP e cor selecionada para a JogoTela

            // Criar um novo Stage para exibir a JogoTela
            Stage stage = new Stage();
            stage.setTitle("Cliente Tela");// Definir o título da janela da JogoTela
            stage.setScene(new Scene(root, 800, 600));// Definir a cena com o conteúdo da JogoTela
            stage.show();

            // Fechar a janela atual da terceira tela
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receberCorSelecionada(String cor) {
        corSelecionada = cor;//Recebe corSelecionada proveniente da SegundaTela
        TextoLabel.setTextFill(Color.web(corSelecionada));// Definir a cor do texto da label com base na cor selecionada
    }

}