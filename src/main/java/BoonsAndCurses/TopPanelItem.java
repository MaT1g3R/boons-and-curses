package BoonsAndCurses;

import BoonsAndCurses.mods.AbstractMod;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class TopPanelItem extends basemod.TopPanelItem {
    private final ModManager m;
    private static final float TIP_OFF_X = 140.0F * Settings.scale;
    private static final float TIP_Y = (float) Settings.HEIGHT - 120.0F * Settings.scale;

    public TopPanelItem(ModManager m) {
        super(ImageMaster.loadImage("img/dice64.png"), "BoonsAndCurses");
        this.m = m;
    }

    @Override
    protected void onClick() {
    }

    @Override
    protected void onHover() {
        super.onHover();
        List<String> lines = new ArrayList<>();
        for (AbstractMod mod : m.activeMods) {
            int cost = mod.getCost();
            String color = "#g";
            if (cost < 0) {
                color = "#r";
            }
            String costString = color + Math.abs(cost);
            if (costString.length() == 3) {
                costString = " " + costString;
            }
            lines.add("[ " + costString + " ] " + mod.name);
        }
        if (!lines.isEmpty()) {
            String body = String.join(" NL ", lines);
            TipHelper.renderGenericTip((float) InputHelper.mX - TIP_OFF_X, TIP_Y, "Active modifiers", body);
        }
    }
}
