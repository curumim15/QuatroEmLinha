package Jogo;

public class Jogador {
    //Atributos do Jogador
    private String nome;
    private String cor;

    //Metodo que retorna o nome do jogador
    public String getName() {
        return nome;
    }

    //Metodo que retorna o nome do jogador
    public void setName(String name) {
        this.nome = name;// Define o nome do jogador
    }

    //Metod que retorna o nome do jogador
    public String getColor() {
        return cor;
    }

    //Metodo que define a cor do jogador
    public void setColor(String cor) {
        this.cor = cor;// Define a cor do jogador
    }
}
