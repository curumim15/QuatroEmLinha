package Jogo;

public class Peca {
    private Jogador jogador;
    private String cor;


    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public boolean isEmpty() {
        return jogador == null;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor){
        this.cor = cor;
    }
}
