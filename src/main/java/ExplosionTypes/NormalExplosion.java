package ExplosionTypes;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class NormalExplosion implements ExplosionType {
    private Block block;

    public NormalExplosion(Block block) {
        this.block = block;
    }

    @Override
    public void explode() {
        this.block.setType(Material.AIR);
        final int x = this.block.getX();
        final int y = this.block.getY();
        final int z = this.block.getZ();

        this.getBlock().getWorld().createExplosion(x, y, z, 4F, false, true);
    }

    @Override
    public NormalExplosion clone() {
        NormalExplosion clone = new NormalExplosion(block);
        return clone;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
