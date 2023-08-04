package Fuzes;

import org.bukkit.Material;
import org.bukkit.block.Block;

import Remote.RemoteObserver;

public class RemoteFuze extends Fuze implements RemoteObserver{
    public RemoteFuze(Block block) {
        super(block);
    }

    @Override
    public void detonate() {
        final double x = this.getBlock().getX();
        final double y = this.getBlock().getY();
        final double z = this.getBlock().getZ();

        this.getBlock().setType(Material.AIR);
        this.getBlock().getWorld().createExplosion(x, y, z, 4F, false, true);
    }

    @Override
    public void fire() {
        this.detonate();
    }
}
