package BoonsAndCurses;

import BoonsAndCurses.mods.AbstractMod;
import BoonsAndCurses.mods.modifiers.CardReward;
import BoonsAndCurses.mods.modifiers.Gold;
import BoonsAndCurses.mods.modifiers.Relic;
import BoonsAndCurses.mods.modifiers.StarterRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModManager {
    private List<AbstractMod> allBoons = new ArrayList<>();
    private List<AbstractMod> allCurses = new ArrayList<>();
    public final List<AbstractMod> activeMods = new ArrayList<>();
    private final Config config;
    public int totalCost = 0;

    public ModManager(Config config) {
        this.config = config;
        resetState();
    }

    public void resetState() {
        Config.Difficulty d = config.getDifficulty();
        totalCost = Config.getDifficultyValue(d);

        activeMods.clear();
        allCurses.clear();
        allBoons.clear();

        List<AbstractMod> allMods =
                Arrays.asList(new Gold.Gain100(),
                        new Gold.Gain250(),
                        new Gold.Lose100(),
                        new Gold.Lose250(),
                        new Relic.Boss(),
                        new Relic.Rare(),
                        new Relic.Uncommon(),
                        new Relic.Shop(),
                        new Relic.Common(),
                        new Relic.Gremlin(),
                        new Relic.HungryFace(),
                        new StarterRelic.Lose(),
                        new StarterRelic.Upgrade(),
                        new CardReward.Lose2Cards(),
                        new CardReward.Lose1Card(),
                        new CardReward.Shard(),
                        new CardReward.Bowl(),
                        new CardReward.QCard(),
                        new CardReward.PWheel());

        allMods.forEach(mod -> {
            if (mod.getCost() < 0) {
                allCurses.add(mod);
            } else {
                allBoons.add(mod);
            }
        });
    }

    public List<AbstractMod> genModList() {
        Config.Difficulty d = config.getDifficulty();
        totalCost = Config.getDifficultyValue(d);

        if (d == Config.Difficulty.Debug) {
            return Stream.concat(allBoons.stream(), allCurses.stream()).sorted().collect(Collectors.toList());
        }

        AbstractMod boon1 = choose(allBoons);

        List<AbstractMod>
                availableBoons =
                allBoons.stream().filter(mod -> mod.getModKind() != boon1.getModKind()).collect(Collectors.toList());
        List<AbstractMod> availableBoonsWithCost = availableBoons.stream().filter(mod -> {
            if (boon1.getCost() <= 5) {
                return mod.getCost() > 5;
            } else {
                return mod.getCost() <= 5;
            }
        }).collect(Collectors.toList());

        AbstractMod boon2 = availableBoonsWithCost.isEmpty() ? choose(availableBoons) : choose(availableBoonsWithCost);

        List<AbstractMod> availableCurses = allCurses.stream().filter(mod ->
                mod.getModKind() != boon1.getModKind() && mod.getModKind() != boon2.getModKind()
        ).collect(Collectors.toList());


        return null;
    }

    private static <T> T choose(List<T> list) {
        return list.get(AbstractDungeon.miscRng.random.nextInt(list.size()));
    }

    public void setSelection(Collection<AbstractCard> selection) {
        this.activeMods.clear();
        for (AbstractCard card : selection) {
            if (card instanceof AbstractMod) {
                AbstractMod mod = (AbstractMod) card;
                this.activeMods.add(mod);
            }
        }
    }

    public void onObtain() {
        activeMods.forEach(AbstractMod::onObtain);
    }
}
