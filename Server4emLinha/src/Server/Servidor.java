package Server;

import Controllers.JogoTela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket servidorSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JogoTela jogoTela;

    public Servidor(JogoTela jogoTela){
        this.jogoTela = jogoTela;
    }

    public void iniciarServidor() {
        Thread serverThread = new Thread(() -> {
            try {
                servidorSocket = new ServerSocket(8888);
                System.out.println("Servidor iniciado. Aguardando conexão do cliente...");
                while (true) {
                    socket = servidorSocket.accept();
                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    jogoTela.esperaClient();
                    jogoTela.enviarNomeP();
                    receberMensagens();
                    new Thread(this::receberMensagens).start();
                }
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
            while (socket.isConnected() && (mensagem = in.readLine()) != null) {
                System.out.println(mensagem);
                if(mensagem.equals("0")||mensagem.equals("1")||mensagem.equals("2")||mensagem.equals("3")||mensagem.equals("4")||mensagem.equals("5")||mensagem.equals("6") ){
                    jogoTela.handleButtonClick(Integer.parseInt(mensagem));
                }else if(mensagem.equals("Desistiu")){
                    jogoTela.SegundoPlayerDesistir();
                }else{
                    jogoTela.segundoPLayer(mensagem);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
