package statergy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStatergy implements WinningStatergy{
    Map<Integer, Map<String, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        String symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }

        Map<String, Integer> colMap = colMaps.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol)+1);

        if(colMap.get(symbol).equals(board.getSize())){
            return true;
        }

        return false;
    }
}
