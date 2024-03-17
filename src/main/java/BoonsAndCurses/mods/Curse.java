package BoonsAndCurses.mods;

public abstract class Curse extends AbstractMod {
    public Curse(String name, String description, int cost) {
        super(name, description, "img/Ascenders_Bane.png", cost, CardType.CURSE, CardRarity.CURSE);
    }

    @Override
    public int getCost() {
        return -this.cost;
    }
}
