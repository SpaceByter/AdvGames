/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.spacebyter.advgames.object;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author Finn
 */
public class CustomHeads {
    
    private final String url;
    private final String name;

    public CustomHeads(String url, String name) {
        this.url = url;
        this.name = name;
    }
    
    public ItemStack createCustomSkull() {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if (this.url.isEmpty())
          return head;
        
        SkullMeta headMeta = (SkullMeta)head.getItemMeta();
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", this.url));
        
        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException|NoSuchFieldException|SecurityException|IllegalAccessException error) {
            error.printStackTrace();
        } 
        head.setItemMeta((ItemMeta)headMeta);
        return head;
    }
  
}
