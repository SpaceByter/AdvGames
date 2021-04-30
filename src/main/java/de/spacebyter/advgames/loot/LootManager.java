package de.spacebyter.advgames.loot;

import de.spacebyter.advgames.AdvGames;
import de.spacebyter.advgames.object.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class LootManager {

    private final AdvGames plugin;
    
    private final ArrayList<Loot> loot = new ArrayList<>();
    
    public LootManager(AdvGames plugin) {
        this.plugin = plugin;
        initLoot();
    }

    private void initLoot() {
        loot.add(new Apple(new ItemBuilder(Material.APPLE).setAmount(5).getItemStack(), 1));
        loot.add(new ChainBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS).getItemStack(), 1));
        loot.add(new Chicken(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(9).getItemStack(), 1));
        loot.add(new CraftingTable(new ItemBuilder(Material.WORKBENCH).getItemStack(), 1));
        loot.add(new DiamondAxe(new ItemBuilder(Material.DIAMOND_AXE).getItemStack(), 1));
        loot.add(new DiamondChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).getItemStack(), 1));
        loot.add(new DiamondLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).getItemStack(), 1));
        loot.add(new GoldenHoe(new ItemBuilder(Material.GOLD_HOE).getItemStack(), 1));
        loot.add(new GoldenLeggings(new ItemBuilder(Material.GOLD_LEGGINGS).getItemStack(), 1));
        loot.add(new IronBoots(new ItemBuilder(Material.IRON_BOOTS).getItemStack(), 1));
        loot.add(new IronSword(new ItemBuilder(Material.IRON_SWORD).getItemStack(), 1));
        loot.add(new LavaBucket(new ItemBuilder(Material.LAVA_BUCKET).getItemStack(), 1));
        loot.add(new LeatherBoots(new ItemBuilder(Material.LEATHER_BOOTS).getItemStack(), 1));

        loot.add(new RegenerationPotion(new Potion(PotionType.REGEN, 1, true ,false).toItemStack(1), 1));

        loot.add(new StoneSword(new ItemBuilder(Material.STONE_SWORD).getItemStack(), 1));
        loot.add(new TNT(new ItemBuilder(Material.TNT).setAmount(4).getItemStack(), 1));
        loot.add(new WaterBucket(new ItemBuilder(Material.WATER_BUCKET).getItemStack(), 1));
        loot.add(new XPBottle(new ItemBuilder(Material.EXP_BOTTLE).setAmount(8).getItemStack(), 1));
    }

    public boolean randomChance(int chance) {
        if((int)(Math.random()*100) < chance) return false;
        return true;
    }

    public ArrayList<Loot> getLoot() {
        return loot;
    }
}
