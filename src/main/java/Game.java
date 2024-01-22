import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Game {
    private static final Scanner scan = new Scanner(System.in);
    private byte i;
    private byte winner = 0;
    private static final Logger logger = LogManager.getLogger(Game.class);
    char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void startGame() {

        logger.info("Enter box number to select. Enjoy!\n");
        boolean boxEmpty = false;

        while (true) {
            createGameField();

            if (!boxEmpty) {
                clearBox();
                boxEmpty = true;
            }

            checkInputX();

            if (checkWinner('X')) {
                winner = 1;
            }
            if (checkDraw()) {
                winner = 3;
            }
            if (checkWinner()) {
                break;
            }

            checkInput0();

            if (checkWinner('O')) {
                winner = 2;
            }
            if (checkDraw()) {
                winner = 3;
            }
            if (checkWinner()) {
                break;
            }
        }
    }

    private void clearBox() {
        for (i = 0; i < 9; i++)
            box[i] = ' ';
    }

    protected void createGameField() {
        logger.info("\n\n {} | {} | {} ", box[0], box[1], box[2]);
        logger.info("-----------");
        logger.info(" {} | {} | {} ", box[3], box[4], box[5]);
        logger.info("-----------");
        logger.info(" {} | {} | {} \n", box[6], box[7], box[8]);
    }

    private boolean checkWinner() {
        if (winner == 1) {
            logger.info("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 2) {
            logger.info("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 3) {
            logger.info("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }
        return false;
    }

    private void checkInputX() {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    logger.info("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                logger.info("Invalid input. Enter again.");
        }
    }

    private void checkInput0() {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private boolean checkWinner(char playerSymbol) {
        return (box[0] == playerSymbol && box[1] == playerSymbol && box[2] == playerSymbol)
                || (box[3] == playerSymbol && box[4] == playerSymbol && box[5] == playerSymbol)
                || (box[6] == playerSymbol && box[7] == playerSymbol && box[8] == playerSymbol)
                || (box[0] == playerSymbol && box[3] == playerSymbol && box[6] == playerSymbol)
                || (box[1] == playerSymbol && box[4] == playerSymbol && box[7] == playerSymbol)
                || (box[2] == playerSymbol && box[5] == playerSymbol && box[8] == playerSymbol)
                || (box[0] == playerSymbol && box[4] == playerSymbol && box[8] == playerSymbol)
                || (box[2] == playerSymbol && box[4] == playerSymbol && box[6] == playerSymbol);
    }

    private boolean checkDraw() {
        boolean boxAvailable = false;
        for (i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }

        return !boxAvailable;
    }
}