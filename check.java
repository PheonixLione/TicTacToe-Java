import javax.swing.*;

// This is an abstract class - it defines what methods must exist
// but does not provide the actual code for some of them
// You cannot create an object of this class directly
abstract class GameChecker {

    // Private fields - these can only be accessed within this class (Encapsulation)
    private boolean gameOver = false;
    private int movesCount = 0;

    // Method to count moves
    public void registerMove() {
        movesCount++;
    }

    // Getter method to access private movesCount
    public int getMovesCount() {
        return movesCount;
    }

    // Getter method to access private gameOver
    public boolean getGameOver() {
        return gameOver;
    }

    // Protected setter - can be used by child classes
    protected void setGameOver(boolean value) {
        gameOver = value;
    }

    // Method to reset the game
    public void reset() {
        gameOver = false;
        movesCount = 0;
    }

    // Abstract methods - child class MUST provide the code for these
    // Notice they have no body, just semicolon at end
    public abstract boolean hasWon(JButton[] buttons, char symbol);

    public abstract char getWinnerSymbol(JButton[] buttons);

    public abstract boolean isGameOver(JButton[] buttons);
}

// This class inherits from GameChecker (Inheritance)
// It must implement all abstract methods from parent class
public class Check extends GameChecker {

    // Private array storing all winning combinations
    private int[][] WINNING_COMBINATIONS = {
            { 0, 1, 2 }, // top row
            { 3, 4, 5 }, // middle row
            { 6, 7, 8 }, // bottom row
            { 0, 3, 6 }, // left column
            { 1, 4, 7 }, // middle column
            { 2, 5, 8 }, // right column
            { 0, 4, 8 }, // diagonal top-left to bottom-right
            { 2, 4, 6 } // diagonal top-right to bottom-left
    };

    // Private helper method to get symbol from button
    private char getSymbol(JButton b) {
        String t = b.getText();
        if (t == null || t.isEmpty())
            return ' ';
        return t.charAt(0);
    }

    // Implementing abstract method from parent (Polymorphism)
    @Override
    public boolean hasWon(JButton[] buttons, char symbol) {
        for (int[] combo : WINNING_COMBINATIONS) {
            if (getSymbol(buttons[combo[0]]) == symbol &&
                    getSymbol(buttons[combo[1]]) == symbol &&
                    getSymbol(buttons[combo[2]]) == symbol) {
                return true;
            }
        }
        return false;
    }

    // Implementing abstract method from parent (Polymorphism)
    @Override
    public char getWinnerSymbol(JButton[] buttons) {
        if (hasWon(buttons, 'X'))
            return 'X';
        if (hasWon(buttons, 'O'))
            return 'O';
        return ' ';
    }

    // Private helper to check if game is a draw
    private boolean isDraw() {
        return getMovesCount() >= 9 && !getGameOver();
    }

    // Implementing abstract method from parent (Polymorphism)
    @Override
    public boolean isGameOver(JButton[] buttons) {
        char winner = getWinnerSymbol(buttons);

        if (winner != ' ') {
            setGameOver(true);
            return true;
        }

        if (isDraw()) {
            setGameOver(true);
            return true;
        }

        return false;
    }
}