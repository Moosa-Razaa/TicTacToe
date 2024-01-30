package TicTacToe;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    public int ComputerTurn(@org.jetbrains.annotations.NotNull ArrayList<Integer> choices)
    {
        Random randomNumber = new Random();
        if(choices.isEmpty()) return -1;
        return choices.get(Math.abs(randomNumber.nextInt() % choices.size()));
    }
}
