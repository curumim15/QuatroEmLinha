package Jogo;

public class Peca {
    //Atributo jogador que representa o jogador dono da peça
    private Jogador jogador;

    //Metodo que retorna o jogador
    public Jogador getJogador() {
        return jogador;
    }
    // Define que define o jogador dono da peça
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;// Define o jogador da peça
    }

    public boolean isEmpty() {
        return jogador == null;// Verifica se a peça está vazia (sem jogador)
    }

}
