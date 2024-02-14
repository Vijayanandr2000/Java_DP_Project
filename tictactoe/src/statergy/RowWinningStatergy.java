package statergy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class RowWinningStatergy implements WinningStatergy{
    HashMap<Integer, HashMap<String, Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }

        String symbol = move.getPlayer().getSymbol();
        HashMap<String, Integer> rowMap = rowMaps.get(row);

        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol, 0);
        }

        rowMap.put(symbol, rowMap.get(symbol)+1);

        return rowMap.get(symbol).equals(board.getSize());
    }
}
