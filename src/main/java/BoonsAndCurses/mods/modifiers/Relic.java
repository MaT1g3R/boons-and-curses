package BoonsAndCurses.mods.modifiers;

import BoonsAndCurses.Utils;
import BoonsAndCurses.mods.Boon;
import BoonsAndCurses.mods.Curse;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.GremlinMask;
import com.megacrit.cardcrawl.relics.NlothsMask;

import static BoonsAndCurses.mods.AbstractMod.ModKind.RELIC;

public class Relic {
    public static class Gremlin extends Curse {
        public Gremlin() {
            super("Gremlin visage", "Gain gremlin visage", 4);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new GremlinMask());
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }

    public static class HungryFace extends Curse {
        public HungryFace() {
            super("Nloth's hungry face", "Gain Nloth's hungry face", 2);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(new NlothsMask());
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }

    public static class Common extends Boon {
        public Common() {
            super("Common relic", "Obtain a random common relic", 3);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(AbstractRelic.RelicTier.COMMON);
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }

    public static class Shop extends Boon {
        public Shop() {
            super("Shop relic", "Obtain a random shop relic", 4);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(AbstractRelic.RelicTier.SHOP);
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }


    public static class Uncommon extends Boon {
        public Uncommon() {
            super("Uncommon relic", "Obtain a random uncommon relic", 5);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(AbstractRelic.RelicTier.UNCOMMON);
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }

    public static class Rare extends Boon {
        public Rare() {
            super("Rare relic", "Obtain a random rare relic", 7);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(AbstractRelic.RelicTier.RARE);
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }

    public static class Boss extends Boon {
        public Boss() {
            super("Boss relic", "Obtain a random boss relic", 10);
        }

        @Override
        public void onObtain() {
            Utils.spawnRelicAndObtain(AbstractRelic.RelicTier.BOSS);
        }

        @Override
        public ModKind getModKind() {
            return RELIC;
        }
    }
}
