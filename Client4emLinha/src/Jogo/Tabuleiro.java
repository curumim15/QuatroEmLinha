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
}
