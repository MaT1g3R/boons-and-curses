package BoonsAndCurses.mods.modifiers;

import BoonsAndCurses.Utils;
import BoonsAndCurses.mods.Boon;
import BoonsAndCurses.mods.Curse;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.BlackBlood;
import com.megacrit.cardcrawl.relics.FrozenCore;
import com.megacrit.cardcrawl.relics.HolyWater;
import com.megacrit.cardcrawl.relics.RingOfTheSerpent;

import static BoonsAndCurses.mods.AbstractMod.ModKind.STARTER_RELIC;

public class StarterRelic {

    public static class Lose extends Curse {
        public Lose() {
            super("Lose starter relic", "Lose your starter relic", 8);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.loseRelic(AbstractDungeon.player.relics.get(0).relicId);
        }

        @Override
        public ModKind getModKind() {
            return STARTER_RELIC;
        }
    }

    public static class Upgrade extends Boon {
        public Upgrade() {
            super("Upgrade starter relic", "Upgrade your starter relic", 10);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.loseRelic(AbstractDungeon.player.relics.get(0).relicId);
            switch (AbstractDungeon.player.chosenClass) {
                case IRONCLAD:
                    Utils.spawnRelicAndObtain(new BlackBlood());
                    break;
                case THE_SILENT:
                    Utils.spawnRelicAndObtain(new RingOfTheSerpent());
                    break;
                case DEFECT:
                    Utils.spawnRelicAndObtain(new FrozenCore());
                    break;
                case WATCHER:
                    Utils.spawnRelicAndObtain(new HolyWater());
                    break;
            }
        }

        @Override
        public ModKind getModKind() {
            return STARTER_RELIC;
        }
    }
}
