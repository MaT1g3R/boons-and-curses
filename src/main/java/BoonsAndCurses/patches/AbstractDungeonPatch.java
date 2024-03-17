package BoonsAndCurses.patches;

import BoonsAndCurses.BoonsAndCurses;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbstractDungeonPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "getRewardCards")
    public static class GetRewardCardsPatch {
        @SpireInsertPatch(rloc = 3, localvars = {"numCards"})
        public static void Insert(@ByRef int[] numCards) {
            BoonsAndCurses.instance.modManager.activeMods.forEach(m ->
                    numCards[0] = numCards[0] + m.changeNumCardReward());
        }
    }
}
