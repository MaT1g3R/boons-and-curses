package BoonsAndCurses;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private final SpireConfig config;
    private static final String DIFF = "difficulty";

    public enum Difficulty {
        Debug,
        Heaven,
        Easy,
        Normal,
        Hard,
        Unfair,
        Brutal,
        Hell,
    }

    public static int getDifficultyValue(Difficulty d) {
        switch (d) {
            case Debug:
                return Integer.MAX_VALUE;
            case Heaven:
                return 10;
            case Easy:
                return 4;
            case Normal:
                return 0;
            case Hard:
                return -4;
            case Unfair:
                return -10;
            case Brutal:
                return -20;
            case Hell:
                return -40;
        }
        return 0;
    }

    public static String getDifficultyDescription(Difficulty d) {
        if (d == Difficulty.Debug) {
            return d.name();
        }
        return d.name() + " (" + getDifficultyValue(d) + ")";
    }

    public Config() throws IOException {
        Properties defaultSettings = new Properties();
        defaultSettings.setProperty(DIFF, Difficulty.Debug.name());

        config = new SpireConfig("BoonsAndCurses", "BoonsAndCursesConfig", defaultSettings);
        config.load();
    }

    public Difficulty getDifficulty() {
        return Difficulty.valueOf(config.getString(DIFF));
    }

    public static Difficulty incrementDifficulty(Difficulty d) {
        int i = d.ordinal() + 1;
        if (i >= Difficulty.values().length) {
            i = 0;
        }
        return Difficulty.values()[i];
    }

    public static Difficulty decrementDifficulty(Difficulty d) {
        int i = d.ordinal() - 1;
        if (i < 0) {
            i = Difficulty.values().length - 1;
        }
        return Difficulty.values()[i];
    }

    public void setDifficulty(Difficulty d) throws IOException {
        config.setString(DIFF, d.name());
        config.save();
    }
}
