import java.awt.*;
import javax.swing.*;

// This class inherits from JFrame to create a window (Inheritance)
public class main extends JFrame {

    // Private fields - only accessible within this class (Encapsulation)
    private JButton[] buttons = new JButton[9];
    private Check game = new Check(); // Using Check class without knowing its internal logic (Abstraction)
    private char currentPlayer = 'X';

    // Constructor - sets up the game window
    public main() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        setResizable(false);
        Font font = new Font("Arial", Font.BOLD, 40);

        // Creating 9 buttons for the game board
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(font);
            final int index = i;
            // Lambda expression implements ActionListener interface (Polymorphism)
            buttons[i].addActionListener(e -> handleButtonClick(index));
            add(buttons[i]);
        }

        setVisible(true);
    }

    // Private method - handles what happens when a button is clicked
    private void handleButtonClick(int index) {
        // If button already has text, ignore click
        if (!buttons[index].getText().equals("")) {
            return;
        }

        // Set the current player's symbol on button
        buttons[index].setText(String.valueOf(currentPlayer));
        game.registerMove();

        // Check if game is over
        if (game.isGameOver(buttons)) {
            char winner = game.getWinnerSymbol(buttons);
            if (winner != ' ') {
                JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            } else {
                JOptionPane.showMessageDialog(this, "It's a draw!");
            }
            resetGame();
        } else {
            // Switch to other player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Private method - resets the game board
    private void resetGame() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
        game.reset();
        currentPlayer = 'X';
    }

    // Main method - entry point of the program
    public static void main(String[] args) {
    new main();
        }
}
