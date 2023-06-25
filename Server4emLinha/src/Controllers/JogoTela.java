package Controllers;

import Server.Servidor;

public class JogoTela {

    private Servidor servidor;

    public void initialize() {
        servidor = new Servidor(this);
        servidor.iniciarServidor();
    }
    public void receberDadosJogo(String text, String text1, String corSelecionada) {
    }
}
