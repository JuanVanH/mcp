package com.juan.pl.consumables;

import com.juan.pl.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsumableListener implements Listener {

    private Main main;
    private final List<IConsumable> consumables = new ArrayList<IConsumable>();


    public ConsumableListener(Main main) {
        this.main = main;
        consumables.add(new JumpGold());
        consumables.add(new SprintMint());
        consumables.add(new StealthDiamond());
    }

    @EventHandler
    public void OnItemUse(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack is = Objects.requireNonNull(e.getPlayer().getItemInHand());
            consumables.forEach(c -> {
                if (c.getName().equals(Objects.requireNonNull(is.getItemMeta()).getDisplayName())) c.consume(e.getPlayer());
            });
            is.setAmount(is.getAmount() - 1);
        }
    }

}
