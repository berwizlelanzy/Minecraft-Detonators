package ExplosionTypes;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class LavaExplosion implements ExplosionType {
    private Block block;

    public LavaExplosion(Block block) {
        this.block = block;
    }

    @Override
    public void explode() {
        this.block.setType(Material.LAVA);
        final int x = this.block.getX();
        final int y = this.block.getY();
        final int z = this.block.getZ();

        this.getBlock().getWorld().createExplosion(x, y, z, 4F, false, true);
    }

    @Override
    public LavaExplosion clone() {
        LavaExplosion clone = new LavaExplosion(block);
        return clone;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
