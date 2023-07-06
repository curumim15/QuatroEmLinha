package Jogo;

public class Tabuleiro {
    //Atributos do Tabuleiro com Peças Colunas e linhas
    private Peca[][] pecas;
    private int rows;
    private int columns;

    //Construtor que recebe o número de linhas e colunas do tabuleiro
    public Tabuleiro(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pecas = new Peca[rows][columns];
        initializePecas();
    }

    //Metodo que Percorre todas as posições do tabuleiro e inicializa cada peça
    private void initializePecas() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                pecas[row][col] = new Peca();// Inicializa cada peça do tabuleiro
            }
        }
    }

    //Metodo que recebe a coluna e o jogador
    //Percorre a colina de baixo para cima e coloca a peça do jogador na primeira posição vazia encontrada
    public int dropPeca(int column, Jogador jogador) {
        for (int row = rows - 1; row >= 0; row--) {
            if (pecas[row][column].isEmpty()) {
                pecas[row][column].setJogador(jogador);// Coloca a peça do jogador na posição especificada
                return row;// Retorna a linha em que a peça foi colocada
            }
        }
        return -1;//Retorna -1 se a coluna estiver cheia
    }

    //Metodo que verifica se o último jogador a jogar venceu
    public boolean checkWin(int lastRow, int lastColumn, Jogador jogador) {
        // Verificação vertical
        int count = 0;
        //loop para percorrer as linhas de cada coluna especifica
        for (int row = 0; row < rows; row++) {
            //Verificar se as peças é do mesmo jogador
            if (pecas[row][lastColumn].getJogador() == jogador) {
                //Se a peça foi do mesmo jogador o count adiciona 1
                count++;
                //Verifica se o count é 4
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na vertical
                }
            } else {//Se as peças não forem do mesmo jogador o count returna a 0
                count = 0;
            }
        }

        // Verificação horizontal
        count = 0;
        //loop para percorrer as colunas de cada linha especifica
        for (int col = 0; col < columns; col++) {
            //Verificar se as peças é do mesmo jogador
            if (pecas[lastRow][col].getJogador() == jogador) {
                //Se a peça foi do mesmo jogador o count adiciona 1
                count++;
                //Verifica se o count é 4
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na horizontal
                }
            } else {//Se as peças não forem do mesmo jogador o count returna a 0
                count = 0;
            }
        }

        // Verificação diagonal ascendente (/)
        count = 0;
        int row = lastRow;
        int col = lastColumn;
        //loop para que percorre as colunas e linhas na diagonal ascendente
        //Continua enquanto a linha for maior ou igual a 0 e o coluna fo menor que o numero de colunas
        //Para garantir que esta dentro dos limites do tabuleiro
        while (row >= 0 && col < columns) {
            //Verificar se as peças é do mesmo jogador
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na diagonal ascendente
                }
            } else {//Se as peças não forem do mesmo jogador o count returna a 0
                count = 0;
            }
            //Atualiza as variaveis para percorrer a diagonal para a direita e para cima
            row--;
            col++;
        }


        row = lastRow +1;
        col = lastColumn-1 ;
        //Novo loop para verificar a diagonal para baixo para a esquerda
        while (row < rows && col >= 0) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na diagonal ascendente
                }
            } else {
                count = 0;
            }
            //Atualiza as variaveis para percorrer a diagonal para a esquerda e para baixo
            row++;
            col--;
        }
        // Verificação diagonal descendente (\)
        count = 0;
        row = lastRow;
        col = lastColumn;
        //loop para que percorre as colunas e linhas na diagonal ascendente
        //Continua enquanto a linha e coluna for maior ou igual a 0
        //Para garantir que esta dentro dos limites do tabuleiro
        while (row >= 0 && col >= 0) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na diagonal ascendente
                }
            } else {//Se as peças não forem do mesmo jogador o count returna a 0
                count = 0;
            }
            //Atualiza as variaveis para percorrer a diagonal para cima e para a esquerda
            row--;
            col--;
        }

        row = lastRow +1;
        col = lastColumn+1;
        //Novo loop para verificar a diagonal para baixo para a direita
        while (row < rows && col < columns) {
            if (pecas[row][col].getJogador() == jogador) {
                count++;
                if (count == 4) {
                    return true;// Verifica se há uma sequência de 4 peças do mesmo jogador na diagonal descendente
                }
            } else {
                count = 0;
            }
            //Atualiza as variaveis para percorrer a diagonal para baixo e para a direita
            row++;
            col++;
        }

        return false; // Não há uma vitória
    }

    //Metodo para verificar o tabuleiro esta cheio
    public boolean isFull() {
        //Loops para percorrer a linhas e as colunas
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (pecas[row][col].isEmpty()) {
                    return false;// Verifica se o tabuleiro está cheio, ou seja, se todas as posições estão ocupadas
                }
            }
        }
        return true;
    }

}
