package net.swofty.types.generic.museum.display;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.metadata.display.ItemDisplayMeta;
import net.swofty.types.generic.entity.hologram.PlayerHolograms;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.museum.MuseumDisplay;
import net.swofty.types.generic.museum.MuseumDisplays;
import net.swofty.types.generic.museum.MuseumableItemCategory;
import net.swofty.types.generic.user.SkyBlockPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.Map;

public class ItemMuseumDisplay extends MuseumDisplay {

    @Override
    public Map.Entry<LivingEntity,
            PlayerHolograms.ExternalPlayerHologram> display(SkyBlockPlayer player,
                                                            MuseumDisplays category,
                                                            @Nullable SkyBlockItem item,
                                                            int position) {
        Pos pos = category.getPositions().get(position);
        PlayerHolograms.ExternalPlayerHologram hologram;
        LivingEntity entity = null;

        if (item == null) {
            hologram = PlayerHolograms.ExternalPlayerHologram.builder()
                    .player(player)
                    .text(new String[]{"§7" + category + " Slot #" + position, "§eCLICK TO EDIT"})
                    .pos(pos.add(0, 1, 0))
                    .build();
            PlayerHolograms.addExternalPlayerHologram(hologram);
        } else {
            hologram = PlayerHolograms.ExternalPlayerHologram.builder()
                    .player(player)
                    .text(new String[]{item.getDisplayName()})
                    .pos(pos.add(0, 1, 0))
                    .build();

            entity = new LivingEntity(EntityType.ITEM_DISPLAY);
            ItemDisplayMeta itemDisplayMeta = (ItemDisplayMeta) entity.getEntityMeta();
            itemDisplayMeta.setItemStack(item.getItemStack());

            entity.setAutoViewable(false);
            entity.setNoGravity(true);
            entity.setInstance(player.getInstance(), pos);
            entity.addViewer(player);
        }

        return new AbstractMap.SimpleEntry<>(entity, hologram);
    }
}