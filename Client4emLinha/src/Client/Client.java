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


    public Client(JogoTela jogoTela){
        this.jogoTela = jogoTela;
    }
    public void conectarAoServidor(String ip) {
        Thread serverThread = new Thread(() -> {
            try {
                socket = new Socket(ip, 8888);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                jogoTela.enviarNomeP();
                new Thread(this::receberMensagens).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

    }

    public void enviarMensagem(String mensagem) {
        if (out != null) {
            out.println(mensagem);
        }
    }

    private void receberMensagens() {
        try {
            String mensagem;
            while ((mensagem = in.readLine()) != null) {
                System.out.println(mensagem);
                if(mensagem.equals("0")||mensagem.equals("1")||mensagem.equals("2")||mensagem.equals("3")||mensagem.equals("4")||mensagem.equals("5")||mensagem.equals("6") ){
                    jogoTela.handleButtonClick(Integer.parseInt(mensagem));
                }else if(mensagem.equals("Desistiu")){
                    jogoTela.SegundoPlayerDesistir();
                } else {
                    jogoTela.segundoPLayer(mensagem);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
