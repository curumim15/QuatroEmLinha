import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carregar o arquivo FXML da Primeira Tela
        Parent root = FXMLLoader.load(getClass().getResource("Views/PrimeiraTela.fxml"));
        primaryStage.setTitle("Server Tela");// Definir o título da janela principal
        primaryStage.setScene(new Scene(root, 800, 600));// Definir a cena com o conteúdo da Primeira Tela
        primaryStage.show();// Exibir a janela principal
    }

    public static void main(String[] args) {
        launch(args);// Iniciar a aplicação JavaFX
    }
}
