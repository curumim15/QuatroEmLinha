package Controllers;

import Client.Client;

public class JogoTela {
    private Client client;

    public void receberDadosJogo(String nome, String ip,String cor) {
        client = new Client(this);
        client.conectarAoServidor(ip);

    }
}
