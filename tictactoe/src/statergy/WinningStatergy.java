package statergy;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStatergy {
    public boolean checkWinner(Board board, Move move);
}
