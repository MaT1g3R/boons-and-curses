package BoonsAndCurses;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModLabeledButton;
import basemod.ModPanel;
import basemod.interfaces.AddCustomModeModsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RunModStrings;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@SpireInitializer
public class BoonsAndCurses
        implements PostInitializeSubscriber, AddCustomModeModsSubscriber, EditStringsSubscriber, StartGameSubscriber {
    public static final Logger logger = LogManager.getLogger(BoonsAndCurses.class.getName());
    public static BoonsAndCurses instance;
    public static final String MOD_ID = "Boons and Curses";
    public CustomMod customMod;
    public ModManager modManager;

    public final Config config;

    public BoonsAndCurses() {
        BaseMod.subscribe(this);
        try {
            config = new Config();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialize() {
        instance = new BoonsAndCurses();
    }

    @Override
    public void receivePostInitialize() {
        modManager = new ModManager(config);


        ModPanel settingsPanel = new ModPanel();

        float xPos = 460;
        float yPos = 650;

        ModLabel
                lbl2 =
                new ModLabel("Difficulty (max modifier points allowed)",
                        xPos - 50 * Settings.scale,
                        yPos + 50 * Settings.scale,
                        settingsPanel,
                        (me) -> {
                        });

        ModLabel
                lbl =
                new ModLabel(Config.getDifficultyDescription(config.getDifficulty()),
                        xPos,
                        yPos + 10 * Settings.scale,
                        settingsPanel,
                        (me) -> {
                        });

        ModLabeledButton left = new ModLabeledButton("<", xPos - 50 * Settings.scale, yPos, settingsPanel, (me) -> {
            try {
                Config.Difficulty d = Config.decrementDifficulty(config.getDifficulty());
                config.setDifficulty(d);
                lbl.text = Config.getDifficultyDescription(d);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ModLabeledButton right = new ModLabeledButton(">", xPos + 90 * Settings.scale, yPos, settingsPanel, (me) -> {
            try {
                Config.Difficulty d = Config.incrementDifficulty(config.getDifficulty());
                config.setDifficulty(d);
                lbl.text = Config.getDifficultyDescription(d);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        settingsPanel.addUIElement(lbl2);
        settingsPanel.addUIElement(lbl);
        settingsPanel.addUIElement(left);
        settingsPanel.addUIElement(right);

        BaseMod.registerModBadge(ImageMaster.loadImage("img/dice.png"),
                "Boons and Curses",
                "vmService",
                "Add a new custom game mode with boons and curses selection",
                settingsPanel);

        BaseMod.addTopPanelItem(new TopPanelItem(modManager));
    }

    @Override
    public void receiveCustomModeMods(List<CustomMod> list) {
        customMod = new CustomMod(MOD_ID, "b", true);
        list.add(customMod);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RunModStrings.class, "run_mods.json");
    }

    public static boolean modEnabled() {
        return AbstractPlayer.customMods.contains(MOD_ID);
    }

    @Override
    public void receiveStartGame() {
        AbstractDungeon.gridSelectScreen.selectedCards.clear();
        modManager.resetState();
    }
}
