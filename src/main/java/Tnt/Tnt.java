package Tnt;

import org.bukkit.block.Block;

import Fuzes.Fuze;

public class Tnt {
    private Fuze fuze;

    public Tnt(Fuze fuze) {
        this.fuze = fuze;
    }

    public Block getBlock() {
        return this.fuze.getBlock();
    }

    public Fuze getFuze() {
        return this.fuze;
    }
}
