import javax.swing.*;

public class Check {
    
    // All possible winning combinations 
    int[][] WINNING_COMBINATIONS = {
        {0, 1, 2}, //rows
        {3, 4, 5}, 
        {6, 7, 8}, 
        {0, 3, 6}, //columns
        {1, 4, 7},   
        {2, 5, 8},   
        {0, 4, 8}, //diagonals
        {2, 4, 6}  
    };
    
    boolean gameOver = false;
    int movesCount = 0;

    void registerMove() {
        movesCount++;
    }

    // helper: get symbol at button i
    char getSymbol(JButton b) {
        String t = b.getText();
        if (t == null || t.isEmpty()) return ' ';
        return t.charAt(0); // 'X' or 'O'
    }

    boolean hasWon(JButton[] buttons, char symbol) {
        for (int[] combo : WINNING_COMBINATIONS) {
            if (getSymbol(buttons[combo[0]]) == symbol &&
                getSymbol(buttons[combo[1]]) == symbol &&
                getSymbol(buttons[combo[2]]) == symbol) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        return movesCount >= 9 && !gameOver;
    }

    char getWinnerSymbol(JButton[] buttons) {
        if (hasWon(buttons, 'X')) return 'X';
        if (hasWon(buttons, 'O')) return 'O';
        return ' ';
    }

     boolean isGameOver(JButton[] buttons) {
        char winner = getWinnerSymbol(buttons);

        if (winner != ' ') {
            gameOver = true;
            return true;
        }

        if (isDraw()) {
            gameOver = true;
            return true;
        }

        gameOver = false;
        return false;
    }

    void reset() {
        gameOver = false;
        movesCount = 0;
    }

}