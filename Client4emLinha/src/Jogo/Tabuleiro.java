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
    public boolean checkWin(int lastRow, int lastColumn, Jogador jogador) {
        // Verificação vertical
        int count = 0;
        for (int row = 0; row < rows; row++) {
            if (pecas[row][lastColumn].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Verificação horizontal
        count = 0;
        for (int col = 0; col < columns; col++) {
            if (pecas[lastRow][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Verificação diagonal ascendente (/)
        count = 0;
        int row = lastRow;
        int col = lastColumn;
        while (row >= 0 && col < columns) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            row--;
            col++;
        }

        row = lastRow + 1;
        col = lastColumn - 1;
        while (row < rows && col >= 0) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            row++;
            col--;
        }
        // Verificação diagonal descendente (\)
        count = 0;
        row = lastRow;
        col = lastColumn;
        while (row >= 0 && col >= 0) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            row--;
            col--;
        }

        row = lastRow + 1;
        col = lastColumn + 1;
        while (row < rows && col < columns) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            row++;
            col++;
        }

        return false; // Não há uma vitória
    }

}
