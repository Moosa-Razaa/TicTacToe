package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeBoard
{
    private char[] _board;
    private final char _playerMarker;
    private final char _computerMarker;
    private char _winner;
    private final Computer _computer;
    Scanner _scanner;

    private void InitializeBoard()
    {
        this._board = new char[9];
        Arrays.fill(this._board, '-');
    }

    private boolean IsInValidRange(int number)
    {
        return (number - 1) < 9 && number > 0;
    }

    private boolean IsIndexAvailable(int number)
    {
        return this._board[number - 1] == '-';
    }

    private int TakeUserInput()
    {
        return _scanner.nextInt();
    }

    private boolean ValidateInput(int number)
    {
        return IsInValidRange(number) && IsIndexAvailable(number);
    }

    private void PrintBoard()
    {
        StringBuilder finalOutput = new StringBuilder();
        for(int i = 0; i < 3; i ++)
        {
            finalOutput.append(String.format("| %c | %c | %c |\n-------------\n", this._board[3 * i], this._board[3 * i + 1], this._board[3 * i + 2]));
        }
        System.out.println(finalOutput + "\n\n>- Indexes of the board -<\n\n");

        finalOutput = new StringBuilder();
        for(int i = 0; i < 3; i ++)
        {
            finalOutput.append(String.format("| %c | %c | %c |\n-------------\n",
                    (this._board[3 * i] == this._playerMarker || this._board[3 * i] == '+') ? this._board[3 * i] : Character.forDigit(3 * i + 1, 10),
                    (this._board[3 * i + 1] == this._playerMarker || this._board[3 * i + 1] == '+') ? this._board[3 * i + 1] : Character.forDigit(3 * i + 2, 10),
                    (this._board[3 * i + 2] == this._playerMarker || this._board[3 * i + 2] == '+') ? this._board[3 * i + 2] : Character.forDigit(3 * i + 3, 10)));
        }
        System.out.println(finalOutput);
    }

    public void PlayGame()
    {
        for(int turns = 0; turns < 5; turns++)
        {
            System.out.print("Please enter the index you want to enter your marker in: ");
            int userInput = TakeUserInput();
            boolean isValidInput = ValidateInput(userInput);

            if(!isValidInput)
            {
                System.out.println("Please enter a valid input. Your input is either at an already occupied index or outside of the range.");
                turns--;
                continue;
            }

            this._board[userInput - 1] = this._playerMarker;
            DecideWinner();

            int computerMarker = _computer.ComputerTurn(GetEmptyPointsInBoard());
            if(computerMarker != -1)
            {
                this._board[computerMarker] = this._computerMarker;
            }
            DecideWinner();
            PrintBoard();

            boolean isGameOver = IsGameOver();
            boolean isBoardFilled = IsBoardFilled();

            if(isBoardFilled)
            {
                System.out.println("The game is drawn!");
                return;
            }

            if(isGameOver)
            {
                System.out.println("The winner is: " + this._winner);
                return;
            }

        }
    }

    private boolean CheckTopLeftToBottomRight()
    {
        return (this._board[0] == this._board[4]) && (this._board[4] == this._board[8]);
    }

    private boolean CheckTopRightToBottomLeft()
    {
        return (this._board[2] == this._board[4]) && (this._board[4] == this._board[6]);
    }

    private boolean CheckMiddleRow()
    {
        return (this._board[3] == this._board[4]) && (this._board[4] == this._board[5]);
    }

    private boolean CheckMiddleColumn()
    {
        return (this._board[1] == this._board[4]) && (this._board[4] == this._board[8]);
    }

    private boolean CheckFirstColumn()
    {
        return (this._board[0] == this._board[3]) && (this._board[3] == this._board[6]);
    }

    private boolean CheckThirdColumn()
    {
        return (this._board[2] == this._board[5]) && (this._board[5] == this._board[8]);
    }

    private boolean CheckFirstRow()
    {
        return (this._board[0] == this._board[1]) && (this._board[1] == this._board[2]);
    }

    private boolean CheckThirdRow()
    {
        return (this._board[6] == this._board[7]) && (this._board[7] == this._board[8]);
    }

    private boolean IsBoardFilled()
    {
        for (char c : this._board) {
            if (c == '-') return false;
        }

        return true;
    }

    private void DecideWinner()
    {
        boolean diagonalsAndMiddles = this._board[4] != '-' && (CheckTopRightToBottomLeft() || CheckTopLeftToBottomRight() || CheckMiddleColumn() || CheckMiddleRow());
        boolean firstRowAndColumn = this._board[0] != '-' && (CheckFirstRow() || CheckFirstColumn());
        boolean thirdRowAndColumn = this._board[8] != '-' && (CheckThirdRow() || CheckThirdColumn());

        if(diagonalsAndMiddles) this._winner = this._board[4];
        else if(firstRowAndColumn) this._winner = this._board[0];
        else if(thirdRowAndColumn) this._winner = this._board[8];
    }

    private boolean IsGameOver()
    {
        return this._winner != '~';
    }

    private ArrayList<Integer> GetEmptyPointsInBoard()
    {
        ArrayList<Integer> arrayList = new ArrayList<>(9);

        for(int i = 0; i < this._board.length; i ++)
        {
            if(this._board[i] == '-') arrayList.add(i);
        }

        return arrayList;
    }

    public TicTacToeBoard(char playerMarker)
    {
        this._playerMarker = playerMarker;
        this._computerMarker = '+';
        this._scanner = new Scanner(System.in);
        this._winner = '~';
        this._computer = new Computer();
        InitializeBoard();
        PrintBoard();
    }

}
