package Client;

import Controllers.JogoTela;
import Controllers.JogoTela;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JogoTela jogoTela;

    //Construtor
    public Client(JogoTela jogoTela){
        this.jogoTela = jogoTela;
    }
    //Metodo que cria uma instância de Thread para estabelecer a conexão com o servidor
    public void conectarAoServidor(String ip) {
        //Cria uma Thread para o Cliente
        Thread serverThread = new Thread(() -> {
            try {
                socket = new Socket(ip, 8888);// Conectar ao servidor utilizando o endereço IP e porta específicos
                out = new PrintWriter(socket.getOutputStream(), true);//Cria um objeto para enviar Mensagens
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// Cria um objeto para receber Mensagens
                jogoTela.enviarNomeP();// Chamar o método enviarNomeP() da JogoTela para enviar o nome do primeiro jogador
                new Thread(this::receberMensagens).start();// Iniciar a receção de mensagens do servidor numa nova Thread
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();// Iniciar a Thread do cliente

    }

    //Metodo para enviar Mensagem para o Servidor
    public void enviarMensagem(String mensagem) {
        if (out != null) {
            out.println(mensagem);// Enviar a mensagem para o Servidor
        }
    }

    //Metodo para Receber Mensagens do Servidor
    private void receberMensagens() {
        try {
            String mensagem;
            //Enquanto o Socket estiver conectado as mensagens são lidas no loop
            while (socket.isConnected() && (mensagem = in.readLine()) != null) {
                System.out.println(mensagem);
                //Restrições para as mensagens lidas
                if(mensagem.equals("0")||mensagem.equals("1")||mensagem.equals("2")||mensagem.equals("3")||mensagem.equals("4")||mensagem.equals("5")||mensagem.equals("6") ){
                    jogoTela.handleButtonClick(Integer.parseInt(mensagem));// Chamar o método handleButtonClick() da JogoTela para tratar o botão clicado pelo servidor
                }else if(mensagem.equals("Desistiu")){
                    jogoTela.SegundoPlayerDesistir();// Chamar o método SegundoPlayerDesistir() da JogoTela caso o segundo jogador desista
                } else {
                    jogoTela.segundoPLayer(mensagem);// Chamar o método segundoPLayer() da JogoTela para tratar a mensagem recebida do servidor
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
