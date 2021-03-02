package net.darkunscripted.Warps.domain;

import org.bukkit.Location;

public class MineWarp {

    private final String name;
    private Location location;

    public MineWarp(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
