package me.eths.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

@Getter @Setter
public class SimpleLocation {

    private double x, y, z;
    private float yaw, pitch;

    public SimpleLocation(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public SimpleLocation getViewed() {
        SimpleLocation location = new SimpleLocation(x, y, z, yaw, pitch);

        if (x < 0) location.setX(Math.ceil(x * 32) / 32);
        else location.setX(Math.floor(x * 32) / 32);

        if (y < 0) location.setY(Math.ceil(y * 32) / 32);
        else location.setY(Math.floor(y * 32) / 32);

        if (z < 0) location.setZ(Math.ceil(z * 32) / 32);
        else location.setZ(Math.floor(z * 32) / 32);

        return location;
    }

    public double distanceXZ(SimpleLocation location) {
        double distanceX = this.x - location.getX();
        double distanceZ = this.z - location.getZ();
        return Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
    }

    public double distanceTo(SimpleLocation location) {
        double distanceX = Math.pow(this.x - location.getX(), 2);
        double distanceY = Math.pow(this.y - location.getY(), 2);
        double distanceZ = Math.pow(this.z - location.getZ(), 2);
        return Math.sqrt(distanceX + distanceY + distanceZ);
    }

    public double distanceXZHitBox(SimpleLocation location, double expansion) {

        double angle = Math.abs(Math.atan2(x - location.getX(), z - location.getZ()) * 180 / Math.PI);
        while(angle > 90) { angle -= 90; }

        double side = expansion * Math.cos(angle);
        return (distanceXZ(location) - Math.sqrt(Math.pow(expansion, 2) + Math.pow(side, 2))) + 0.03;

    }

}
