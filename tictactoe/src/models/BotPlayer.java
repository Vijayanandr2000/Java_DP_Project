package models;

import enums.CellState;
import enums.DifficultyLevel;
import enums.PlayerType;
import factory.BotPlayingFactory;
import statergy.BotStatergy;

public class BotPlayer extends Player {
    private PlayerType playerType;
    private BotStatergy botStatergy;

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public BotStatergy getBotStatergy() {
        return botStatergy;
    }

    public void setBotStatergy(BotStatergy botStatergy) {
        this.botStatergy = botStatergy;
    }

    public BotPlayer(String name, int id, String symbol, PlayerType playerType) {
        super(name, id, symbol);
        this.playerType = playerType;
        this.botStatergy = BotPlayingFactory.getBotPlayingStatergy(DifficultyLevel.EASY);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    Cell makeMove(Board board) {
        System.out.println("Bot is Moving");
        Cell cell = botStatergy.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);

        return cell;
    }

    public void setPlayerType(String playerType) {
        this.playerType = PlayerType.valueOf(playerType);
    }
}
