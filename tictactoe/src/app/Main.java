package app;

import controller.GameController;
import enums.GameState;
import enums.PlayerType;
import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import exceptions.MoreThanOneBotException;
import models.BotPlayer;
import models.Game;
import models.HumanPlayer;
import models.Player;
import statergy.ColWinningStatergy;
import statergy.DiagonalWinningStatery;
import statergy.RowWinningStatergy;
import statergy.WinningStatergy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolException, InvalidPlayerCountException, MoreThanOneBotException {
        Scanner scanner = new Scanner(System.in);

        GameController gameController = new GameController();

        List<Player> players = new ArrayList<>();

        players.add(new HumanPlayer("Vijay",1,"X", PlayerType.HUMAN, scanner));
        players.add(new BotPlayer("Anand", 2, "O", PlayerType.BOT));

        int size = 3;

        List<WinningStatergy> winningStatergies = new ArrayList<>();

        winningStatergies.add(new RowWinningStatergy());
        winningStatergies.add(new ColWinningStatergy());
        winningStatergies.add(new DiagonalWinningStatery());

        Game game = gameController.createGame(players, size, winningStatergies);

        while (GameState.IN_PROG.equals(game.getGameState())){
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("The Match is Draw");
            return;
        }

        if(GameState.DONE.equals(game.getGameState())){
            System.out.println("The Winner is " + game.getWinner().getName());
            return;
        }


    }
}
