package controller;

import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import exceptions.MoreThanOneBotException;
import models.Game;
import models.Player;
import statergy.WinningStatergy;

import java.util.List;

public class GameController {

    public Game createGame(List<Player> players, int size, List<WinningStatergy> winningStatergies) throws DuplicateSymbolException, InvalidPlayerCountException, MoreThanOneBotException {
        return Game.getBuilder()
                .setPlayers(players)
                .setSize(size)
                .setWinningStatergies(winningStatergies)
                .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }
}
