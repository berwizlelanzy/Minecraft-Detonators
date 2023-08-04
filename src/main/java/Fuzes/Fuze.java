package Fuzes;

import org.bukkit.block.Block;

public abstract class Fuze {
    private Block block;

    public Fuze(Block block) {
        this.block = block;
    }
    
    public abstract void detonate();
    
    public Block getBlock() {
        return this.block;
    }
}
