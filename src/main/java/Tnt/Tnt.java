package Tnt;

import org.bukkit.Material;
import org.bukkit.block.Block;

import Fuzes.Fuze;

public class Tnt implements Detonatable {
    private Fuze fuze;
    private Block block;

    public Tnt(Block block, Fuze fuze) {
        this.block = block;
        this.fuze = fuze;
        this.fuze.addDetonator(this::detonate);
    }

    public Block getBlock() {
        return this.block;
    }

    public Fuze getFuze() {
        return this.fuze;
    }

    @Override
    public void detonate() {
        this.block.setType(Material.AIR);
        final int x = this.getBlock().getX();
        final int y = this.getBlock().getY();
        final int z = this.getBlock().getZ();

        this.getBlock().getWorld().createExplosion(x, y, z, 4F, false, true);
    }
}
