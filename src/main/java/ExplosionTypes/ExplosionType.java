package ExplosionTypes;

import org.bukkit.block.Block;

public interface ExplosionType {
    public abstract void explode();
    public abstract Block getBlock();
    public abstract ExplosionType clone();
}
