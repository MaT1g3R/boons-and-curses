package BoonsAndCurses.mods.modifiers;

import BoonsAndCurses.mods.Boon;
import BoonsAndCurses.mods.Curse;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static BoonsAndCurses.mods.AbstractMod.ModKind.GOLD;

public class Gold {
    public static class Lose250 extends Curse {
        public Lose250() {
            super("Lose 250 gold", "Lose 250 gold", 6);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.gold -= 250;
        }

        @Override
        public ModKind getModKind() {
            return GOLD;
        }
    }

    public static class Lose100 extends Curse {
        public Lose100() {
            super("Lose 100 gold", "Lose 100 gold", 2);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.gold -= 100;
        }

        @Override
        public ModKind getModKind() {
            return GOLD;
        }
    }

    public static class Gain100 extends Boon {
        public Gain100() {
            super("Gain 100 gold", "Gain 100 gold", 3);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.gainGold(100);
        }

        @Override
        public ModKind getModKind() {
            return GOLD;
        }
    }

    public static class Gain250 extends Boon {
        public Gain250() {
            super("Gain 250 gold", "Gain 250 gold", 7);
        }

        @Override
        public void onObtain() {
            AbstractDungeon.player.gainGold(250);
        }

        @Override
        public ModKind getModKind() {
            return GOLD;
        }
    }
}

