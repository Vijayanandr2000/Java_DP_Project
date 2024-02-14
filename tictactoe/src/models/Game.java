package models;

import enums.GameState;
import enums.PlayerType;
import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import exceptions.MoreThanOneBotException;
import statergy.WinningStatergy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> playerList;
    private Board board;
    private GameState gameState;
    private int nextPlayerIdx;
    private Player winner;
    private List<Move> moves;
    private List<WinningStatergy> winningStatergies;

    public Game(List<Player> players, int size, List<WinningStatergy> winningStatergies) {
        this.playerList = players;
        this.board = new Board(size);
        this.gameState = GameState.IN_PROG;
        this.nextPlayerIdx = 0;
        this.winningStatergies = winningStatergies;
        this.moves = new ArrayList<>();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIdx() {
        return nextPlayerIdx;
    }

    public void setNextPlayerIdx(int nextPlayerIdx) {
        this.nextPlayerIdx = nextPlayerIdx;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStatergy> getWinningStatergies() {
        return winningStatergies;
    }

    public void setWinningStatergies(List<WinningStatergy> winningStatergies) {
        this.winningStatergies = winningStatergies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = playerList.get(nextPlayerIdx);

        Cell cell = player.makeMove(board);

        Move move = new Move(cell, player);
        moves.add(move);

        if(winner(board, move)){
            this.gameState = GameState.DONE;
            this.winner = player;
            return;
        }
        if(moves.size() == board.getSize() * board.getSize()){
            this.gameState = GameState.DRAW;
            return;
        }

        nextPlayerIdx++;
        nextPlayerIdx = nextPlayerIdx % playerList.size();

    }

    private boolean winner(Board board, Move move) {
        for (WinningStatergy winningStatergy:winningStatergies){
            if(winningStatergy.checkWinner(board, move)){
                return true;
            }
        }

        return false;
    }

    public static class Builder{
        private List<Player> players;
        private int size;
        private List<WinningStatergy> winningStatergies;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setWinningStatergies(List<WinningStatergy> winningStatergies) {
            this.winningStatergies = winningStatergies;
            return this;
        }

        public Game build() throws MoreThanOneBotException, InvalidPlayerCountException, DuplicateSymbolException {
            ValidateBotCount();
            ValidateNumberOfPlayers();
            ValidateEachPlayerSymbol();

            return new Game(players, size, winningStatergies);
        }

        private void ValidateEachPlayerSymbol() throws DuplicateSymbolException {
            HashSet<String> set = new HashSet<>();

            for(Player player : players){
                if(set.contains(player.getSymbol())){
                    throw new DuplicateSymbolException();
                }
                set.add(player.getSymbol());
            }
        }

        private void ValidateNumberOfPlayers() throws InvalidPlayerCountException {
            if(players.size() != size - 1){
                throw new InvalidPlayerCountException();
            }
        }

        private void ValidateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player:players){
                if(PlayerType.BOT.equals(player.getPlayerType())){
                    botCount++;

                    if (botCount > 1){
                        throw new MoreThanOneBotException();
                    }
                }
            }
        }
    }
}
