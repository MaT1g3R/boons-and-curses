package BoonsAndCurses.patches;

import BoonsAndCurses.BoonsAndCurses;
import BoonsAndCurses.Config;
import BoonsAndCurses.mods.AbstractMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

public class NeowEventPatch {
    public static boolean clicked = false;
    public static boolean done = false;
    public static boolean needsFinalizing = false;

    @SpirePatch(clz = NeowEvent.class, method = "dailyBlessing")
    public static class DailyBlessingPatch {
        @SpireInsertPatch(rloc = 7, localvars = {"rng"})
        public static void Insert(NeowEvent __instance, Random rng) {
            clicked = false;
            done = false;
            needsFinalizing = false;

            if (BoonsAndCurses.modEnabled()) {
                CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                g.group.addAll(BoonsAndCurses.instance.modManager.genModList());
                AbstractDungeon.gridSelectScreen.open(g,
                        g.size(),
                        true,
                        Config.getDifficultyDescription(BoonsAndCurses.instance.config.getDifficulty()));
            }
        }


        @SpirePatch(clz = AbstractDungeon.class, method = "closeCurrentScreen")
        public static class CloseCurrentScreenPatch {
            public static void Postfix() {
                if (needsFinalizing) {
                    needsFinalizing = false;
                    BoonsAndCurses.instance.modManager.onObtain();
                }
            }
        }


        @SpirePatch(clz = GridCardSelectScreen.class, method = "update")
        public static class UpdatePatch {
            public static void Prefix(GridCardSelectScreen __instance) {
                if (done) {
                    return;
                }

                if (!BoonsAndCurses.modEnabled() || AbstractDungeon.floorNum != 0) {
                    return;
                }
                __instance.confirmButton.update();
                clicked = __instance.confirmButton.hb.clicked;
                if (clicked) {
                    BoonsAndCurses.instance.modManager.setSelection(__instance.selectedCards);
                    __instance.selectedCards.clear();
                    __instance.confirmButton.isDisabled = false;
                    clicked = false;
                    done = true;
                    needsFinalizing = true;
                } else {
                    int
                            selectedCost =
                            __instance.selectedCards.stream()
                                    .map(c -> (AbstractMod) c)
                                    .map(AbstractMod::getCost)
                                    .reduce(Integer::sum)
                                    .orElse(0);
                    __instance.confirmButton.updateText("Selected cost: " + selectedCost);
                    __instance.confirmButton.isDisabled = selectedCost > BoonsAndCurses.instance.modManager.totalCost;
                }
            }
        }
    }
}
