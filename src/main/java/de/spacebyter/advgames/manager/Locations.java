package de.spacebyter.advgames.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Finn
 */
public class Locations {
    
    private final File file = new File("plugins/AdvGames/location.yml");
    private final FileConfiguration fileConfiguration;

    public Locations() {
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
    
    //<editor-fold defaultstate="collapsed" desc="addLocation">
    public void addLocation(Location location, String locationName) throws IOException {
        this.fileConfiguration.set(locationName + ".X", location.getX());
        this.fileConfiguration.set(locationName + ".Y", location.getY());
        this.fileConfiguration.set(locationName + ".Z", location.getZ());
        this.fileConfiguration.set(locationName + ".YAW", location.getYaw());
        this.fileConfiguration.set(locationName + ".PITCH", location.getPitch());
        this.fileConfiguration.set(locationName + ".WORLD", location.getWorld().getName());
        this.fileConfiguration.save(file);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getLocation">
    public Location getLocation(String name) {
        double x = this.fileConfiguration.getDouble(name + ".X");
        double y = this.fileConfiguration.getDouble(name + ".Y");
        double z = this.fileConfiguration.getDouble(name + ".Z");
        float pitch = Float.valueOf(this.fileConfiguration.getString(name + ".PITCH"));
        float yaw = Float.valueOf(this.fileConfiguration.getString(name + ".YAW"));
        String world = this.fileConfiguration.getString(name + ".WORLD");
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="locationToString">
    public String locationToString(Location location) {
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location
                .getYaw() + "," + location.getPitch();
    }
    //</editor-fold>
  
    //<editor-fold defaultstate="collapsed" desc="locationFromString">
    public Location locationFromString(String location) {
        String[] split = location.split(",");
        return new Location(Bukkit.getWorld(split[0]), Double.valueOf(split[1]).doubleValue(), Double.valueOf(split[2]).doubleValue(), Double.valueOf(split[3]).doubleValue(), Float.valueOf(split[4]).floatValue(), Float.valueOf(split[5]).floatValue());
    }
    //</editor-fold>

}
