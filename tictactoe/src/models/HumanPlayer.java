package models;

import enums.CellState;
import enums.PlayerType;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private PlayerType playerType;

    public Scanner scanner;

    public  HumanPlayer(String name, int id, String symbol, PlayerType playerType, Scanner scanner){
        super(name, id, symbol);
        this.playerType = playerType;
        this.scanner = scanner;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println(this.getName() + " Your Turn Select the Row and Col");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while (!valid(row, col, board)){
            System.out.println(this.getName() + " Invalid row and col Again Select the valid row and col");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);

        return cell;
    }

    private boolean valid(int row, int col, Board board) {
        if(row >= board.getSize() || row < 0){
            return false;
        }
        if(col >= board.getSize() || col < 0){
            return false;
        }
        return true;
    }

    public void setPlayerType(String playerType) {
        this.playerType = PlayerType.valueOf(playerType);
    }
}
