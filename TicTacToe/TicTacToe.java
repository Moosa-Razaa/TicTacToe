package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        char userMarker;

        System.out.println("The marker \"+\" is reserved for COMPUTER.\n---------------------------------------------------------------------\nYour marker must contain with only one character\n---------------------------------------------------------------------");

        while(true)
        {
            System.out.print("Please enter your marker: ");
            userMarker = userInput.next().charAt(0);

            if(userMarker == '+') System.out.println("You can't enter the reserved marker.");
            else break;
        }

        TicTacToeBoard board = new TicTacToeBoard(userMarker);
        board.PlayGame();

        System.out.println("GAME FINISHED!");
    }
}
