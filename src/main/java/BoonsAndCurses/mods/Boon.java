package BoonsAndCurses.mods;

public abstract class Boon extends AbstractMod {
    public Boon(String name, String description, int cost) {
        super(name, description, "img/Biased_Cognition.png", cost, CardType.POWER, CardRarity.UNCOMMON);
    }

    @Override
    public int getCost() {
        return this.cost;
    }
}
