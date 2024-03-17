package BoonsAndCurses.mods;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractMod extends CustomCard {
    public AbstractMod(String name, String description, String img, int cost, CardType type, CardRarity rarity) {
        super(name, name, img, cost, description, type, CardColor.COLORLESS, rarity, CardTarget.NONE);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    public void onObtain() {
    }

    public int changeNumCardReward() {
        return 0;
    }

    @Override
    public int compareTo(AbstractCard other) {
        if (!(other instanceof AbstractMod)) {
            return super.compareTo(other);
        }
        AbstractMod otherMod = (AbstractMod) other;
        if (this.getCost() != otherMod.getCost()) {
            return this.getCost() - otherMod.getCost();
        }
        if (this.getModKind() != otherMod.getModKind()) {
            return this.getModKind().ordinal() - otherMod.getModKind().ordinal();
        }
        return super.compareTo(other);
    }


    public abstract int getCost();

    public abstract ModKind getModKind();

    public enum ModKind {
        STARTER_RELIC, RELIC, GOLD, CARD_REWARD, POTION
    }
}
