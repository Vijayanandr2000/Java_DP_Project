package factory;

import enums.DifficultyLevel;
import statergy.BotStatergy;
import statergy.EasyBotPlayingStatergy;

public class BotPlayingFactory {
    public static BotStatergy getBotPlayingStatergy(DifficultyLevel difficultyLevel){
        return new EasyBotPlayingStatergy();
    }
}
