package Jogo;

public class Tabuleiro {
    private Peca[][] pecas;
    private int rows;
    private int columns;

    public Tabuleiro(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pecas = new Peca[rows][columns];
        initializePecas();
    }

    private void initializePecas() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                pecas[row][col] = new Peca();
            }
        }
    }
    public Peca getPeca(int row, int column) {
        return pecas[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    public int dropPeca(int column, Jogador jogador) {
        for (int row = rows - 1; row >= 0; row--) {
            if (pecas[row][column].isEmpty()) {
                pecas[row][column].setJogador(jogador);
                return row;
            }
        }
        return -1;
    }

}
