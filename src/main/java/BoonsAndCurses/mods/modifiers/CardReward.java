package BoonsAndCurses.mods.modifiers;

import BoonsAndCurses.Utils;
import BoonsAndCurses.mods.Boon;
import BoonsAndCurses.mods.Curse;
import com.megacrit.cardcrawl.relics.PrayerWheel;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.relics.QuestionCard;
import com.megacrit.cardcrawl.relics.SingingBowl;

import static BoonsAndCurses.mods.AbstractMod.ModKind.CARD_REWARD;

public class CardReward {
    public static class Lose2Cards extends Curse {
        public Lose2Cards() {
            super("Card reward -2", "Card rewards only contain 1 card", 9);
        }

        @Override
        public int changeNumCardReward() {
            return -2;
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }

    public static class Lose1Card extends Curse {
        public Lose1Card() {
            super("Card reward -1", "Card rewards only contain 2 cards", 6);
        }

        @Override
        public int changeNumCardReward() {
            return -1;
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }

    public static class Shard extends Curse {
        public Shard() {
            super("Shard", "Obtain prismatic shard", 1);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new PrismaticShard());
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }

    public static class Bowl extends Boon {
        public Bowl() {
            super("Singing Bowl", "Obtain singing bowl", 5);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new SingingBowl());
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }

    public static class QCard extends Boon {
        public QCard() {
            super("Question Card", "Obtain question card", 7);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new QuestionCard());
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }

    public static class PWheel extends Boon {
        public PWheel() {
            super("Prayer Wheel", "Obtain prayer wheel", 10);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new PrayerWheel());
        }

        @Override
        public ModKind getModKind() {
            return CARD_REWARD;
        }
    }
}
