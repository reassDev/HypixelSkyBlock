package net.swofty.types.generic.item.items.combat.mythological.craftable;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.ItemQuantifiable;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.*;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.commons.statistics.ItemStatistic;
import net.swofty.commons.statistics.ItemStatistics;

import java.util.*;

public class SwordOfRevelations implements CustomSkyBlockItem, DefaultCraftable, StandardItem, NotFinishedYet {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, ItemQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new ItemQuantifiable(ItemTypeLinker.GRIFFIN_FEATHER, 32));
        ingredientMap.put('B', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_ANCIENT_CLAW, 8));
        ingredientMap.put('C', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_GOLD_BLOCK, 8));
        ingredientMap.put('D', new ItemQuantifiable(ItemTypeLinker.STICK, 1));
        List<String> pattern = List.of(
                "ABA",
                "ACA",
                "ADA");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.NONE, new SkyBlockItem(ItemTypeLinker.SWORD_OF_REVELATIONS), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.builder()
                .withBase(ItemStatistic.DAMAGE, 180D)
                .withBase(ItemStatistic.STRENGTH, 50D)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§7Heal §c15❤ §7per hit.",
                "",
                "§7Deals §a+200% §7damage against",
                "§7mythological creatures and Minos",
                "§7followers but receive §c+75%",
                "§c§7damage from them."));
    }

    @Override
    public StandardItemType getStandardItemType() {
        return StandardItemType.SWORD;
    }
}
